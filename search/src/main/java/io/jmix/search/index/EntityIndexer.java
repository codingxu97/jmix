/*
 * Copyright 2020 Haulmont.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.jmix.search.index;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.*;
import io.jmix.core.*;
import io.jmix.core.entity.EntityValues;
import io.jmix.core.metamodel.model.MetaClass;
import io.jmix.data.EntityChangeType;
import io.jmix.search.index.mapping.AnnotatedIndexDefinitionsProvider;
import io.jmix.search.index.queue.QueueItem;
import io.jmix.search.utils.PropertyTools;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

@Component
public class EntityIndexer {

    private static final Logger log = LoggerFactory.getLogger(EntityIndexer.class);

    @Autowired
    protected DataManager dataManager;
    @Autowired
    protected FetchPlans fetchPlans;
    @Autowired
    protected RestHighLevelClient esClient;
    @Autowired
    protected AnnotatedIndexDefinitionsProvider indexDefinitionsProvider;
    @Autowired
    protected Metadata metadata;
    @Autowired
    protected PropertyTools propertyTools;

    protected ObjectMapper objectMapper = new ObjectMapper();

    public void indexEntities(Collection<QueueItem> queueItems) {
        log.trace("[IVGA] Index items: {}", queueItems);
        Map<MetaClass, Map<EntityChangeType, Set<String>>> indexScope = new HashMap<>();
        queueItems.forEach(queueItem -> {
            MetaClass metaClass = metadata.getClass(queueItem.getEntityName());
            Map<EntityChangeType, Set<String>> changesByClass = indexScope.computeIfAbsent(metaClass, k -> new HashMap<>());
            Set<String> changesByChangeType = changesByClass.computeIfAbsent(EntityChangeType.fromId(queueItem.getChangeType()), k -> new HashSet<>());
            changesByChangeType.add(queueItem.getEntityId());
        });

        indexScope.forEach(
                (metaClass, changes) -> changes.forEach(
                        (entityChangeType, pks) -> indexEntitiesByPks(metaClass, pks, entityChangeType)
                )
        );
    }

    public void indexEntityByPk(MetaClass metaClass, String entityPk, EntityChangeType changeType) {
        indexEntitiesByPks(metaClass, Collections.singletonList(entityPk), changeType);
    }

    public void indexEntitiesByPks(MetaClass metaClass, Collection<String> entityPks, EntityChangeType changeType) {
        log.info("[IVGA] Index entities: Class={}, changeType={}, pks={}", metaClass, changeType, entityPks);
        IndexDefinition indexDefinition = indexDefinitionsProvider.getIndexDefinitionByEntityName(metaClass.getName());
        if(indexDefinition == null) {
            log.error("[IVGA] Index Definition not found for entity '{}'", metaClass);
            return;
        }
        log.info("[IVGA] Mapping Fields for entity '{}': {}", metaClass, indexDefinition.getMapping().getFields());

        if(EntityChangeType.UPDATE.equals(changeType) || EntityChangeType.CREATE.equals(changeType)) {
            String primaryKeyPropertyName = propertyTools.getPrimaryKeyPropertyNameForSearch(metaClass);
            FetchPlan fetchPlan = createFetchPlan(indexDefinition);
            log.info("[IVGA] Fetch plan for class {}: {}", metaClass, fetchPlan.getProperties());
            List<Object> loaded = dataManager.load(metaClass.getJavaClass())
                    .query(String.format("select e from %s e where e.%s in :ids", metaClass.getName(), primaryKeyPropertyName))
                    .parameter("ids", entityPks)
                    .fetchPlan(fetchPlan)
                    .list();
            log.info("[IVGA] Loaded entities: {}", loaded);

            BulkRequest request = new BulkRequest(indexDefinition.getIndexName());
            loaded.forEach(entity -> {
                ObjectNode entityIndexContent = JsonNodeFactory.instance.objectNode();
                indexDefinition.getMapping().getFields().values().stream()
                        .filter(field -> !field.isStandalone())
                        .forEach(field -> {
                            log.trace("[IVGA] Extract value of property {}", field.getMetaPropertyPath());
                            JsonNode propertyValue = field.getValue(entity);
                            if(!propertyValue.isNull()) {
                                String indexPropertyFullName = field.getIndexPropertyFullName();

                                ObjectNode objectNodeForField = createObjectNodeForField(indexPropertyFullName, propertyValue);
                                log.trace("[IVGA] objectNodeForField = {}", objectNodeForField);
                                merge(objectNodeForField, entityIndexContent);
                            }
                        });

                ObjectNode resultObject = JsonNodeFactory.instance.objectNode();
                resultObject.putObject("meta").put("entityClass", metaClass.getName());
                resultObject.set("content", entityIndexContent);

                log.info("[IVGA] INDEX OBJECT {}: Result Json = {}", entity, resultObject);
                try {
                    Object primaryKey = EntityValues.getValue(entity, primaryKeyPropertyName);
                    if(primaryKey == null) {
                        log.error("[IVGA] Unable to index instance '{}({})': Primary key not found", metaClass.getName(), EntityValues.getId(entity));
                    } else {
                        request.add(new IndexRequest()
                                .id(primaryKey.toString())
                                .source(objectMapper.writeValueAsString(resultObject), XContentType.JSON));
                    }
                } catch (JsonProcessingException e) {
                    throw new RuntimeException("Failed to create index request: unable to parse source object", e);
                }
            });

            try {
                BulkResponse bulkResponse = esClient.bulk(request, RequestOptions.DEFAULT);
                log.info("[IVGA] Bulk Response (Index): Took {}, Status = {}, With Failures = {}",
                        bulkResponse.getTook(), bulkResponse.status(), bulkResponse.hasFailures());
            } catch (IOException e) {
                throw new RuntimeException("Bulk request failed", e);
            }
        } else if(EntityChangeType.DELETE.equals(changeType)) {
            BulkRequest request = new BulkRequest(indexDefinition.getIndexName());
            entityPks.forEach(id -> request.add(new DeleteRequest().id(id)));

            try {
                BulkResponse bulkResponse = esClient.bulk(request, RequestOptions.DEFAULT);
                log.info("[IVGA] Bulk Response (Delete): Took {}, Status = {}, With Failures = {}",
                        bulkResponse.getTook(), bulkResponse.status(), bulkResponse.hasFailures());
            } catch (IOException e) {
                throw new RuntimeException("Bulk request failed", e);
            }
        } else {
            throw new UnsupportedOperationException("Entity Change Type '" + changeType + "' is not supported");
        }
    }

    protected FetchPlan createFetchPlan(IndexDefinition indexDefinition) {
        FetchPlanBuilder fetchPlanBuilder = fetchPlans.builder(indexDefinition.getEntityClass());
        MetaClass metaClass = metadata.getClass(indexDefinition.getEntityName());
        String primaryKeyPropertyName = propertyTools.getPrimaryKeyPropertyNameForSearch(metaClass);
        fetchPlanBuilder.add(primaryKeyPropertyName);
        indexDefinition.getMapping().getFields().values().forEach(field -> {
            log.info("[IVGA] Add property to fetch plan: {}", field.getEntityPropertyFullName());
            fetchPlanBuilder.add(field.getEntityPropertyFullName());
            field.getInstanceNameRelatedProperties().forEach(instanceNameRelatedProperty -> {
                log.info("[IVGA] Add instance name related property to fetch plan: {}", instanceNameRelatedProperty.toPathString());
                fetchPlanBuilder.add(instanceNameRelatedProperty.toPathString());
            });
        });
        return fetchPlanBuilder.build();
    }

    protected ObjectNode createObjectNodeForField(String key, JsonNode value) {
        ObjectNode root = JsonNodeFactory.instance.objectNode();
        String[] fields = key.split("\\.");
        ObjectNode currentRoot = root;
        for(int i = 0; i < fields.length; i++) {
            String field = fields[i];
            if(i == fields.length - 1) {
                currentRoot.set(field, value);
            } else {
                currentRoot = currentRoot.putObject(field);
            }
        }
        return root;
    }

    //todo move to tools?
    public void merge(JsonNode toBeMerged, JsonNode mergedInTo) {
        log.trace("[IVGA] Merge object {} into {}", toBeMerged, mergedInTo);
        Iterator<Map.Entry<String, JsonNode>> incomingFieldsIterator = toBeMerged.fields();
        Iterator<Map.Entry<String, JsonNode>> mergedIterator;

        while (incomingFieldsIterator.hasNext()) {
            Map.Entry<String, JsonNode> incomingEntry = incomingFieldsIterator.next();

            JsonNode subNode = incomingEntry.getValue();

            if (subNode.getNodeType().equals(JsonNodeType.OBJECT)) {
                boolean isNewBlock = true;
                mergedIterator = mergedInTo.fields();
                while (mergedIterator.hasNext()) {
                    Map.Entry<String, JsonNode> entry = mergedIterator.next();
                    if (entry.getKey().equals(incomingEntry.getKey())) {
                        merge(incomingEntry.getValue(), entry.getValue());
                        isNewBlock = false;
                    }
                }
                if (isNewBlock) {
                    ((ObjectNode) mergedInTo).replace(incomingEntry.getKey(), incomingEntry.getValue());
                }
            } else if (subNode.getNodeType().equals(JsonNodeType.ARRAY)) {
                boolean newEntry = true;
                mergedIterator = mergedInTo.fields();
                while (mergedIterator.hasNext()) {
                    Map.Entry<String, JsonNode> entry = mergedIterator.next();
                    if (entry.getKey().equals(incomingEntry.getKey())) {
                        updateArray(incomingEntry.getValue(), entry);
                        newEntry = false;
                    }
                }
                if (newEntry) {
                    ((ObjectNode) mergedInTo).replace(incomingEntry.getKey(), incomingEntry.getValue());
                }
            }
            ValueNode valueNode = null;
            JsonNode incomingValueNode = incomingEntry.getValue();
            switch (subNode.getNodeType()) {
                case STRING:
                    valueNode = new TextNode(incomingValueNode.textValue());
                    break;
                case NUMBER:
                    valueNode = new IntNode(incomingValueNode.intValue());
                    break;
                case BOOLEAN:
                    valueNode = BooleanNode.valueOf(incomingValueNode.booleanValue());
            }
            if (valueNode != null) {
                updateObject(mergedInTo, valueNode, incomingEntry);
            }
        }
    }

    private void updateArray(JsonNode valueToBePlaced, Map.Entry<String, JsonNode> toBeMerged) {
        toBeMerged.setValue(valueToBePlaced);
    }

    private void updateObject(JsonNode mergeInTo, ValueNode valueToBePlaced,
                                     Map.Entry<String, JsonNode> toBeMerged) {
        boolean newEntry = true;
        Iterator<Map.Entry<String, JsonNode>> mergedIterator = mergeInTo.fields();
        while (mergedIterator.hasNext()) {
            Map.Entry<String, JsonNode> entry = mergedIterator.next();
            if (entry.getKey().equals(toBeMerged.getKey())) {
                newEntry = false;
                entry.setValue(valueToBePlaced);
            }
        }
        if (newEntry) {
            ((ObjectNode) mergeInTo).replace(toBeMerged.getKey(), toBeMerged.getValue());
        }
    }
}
