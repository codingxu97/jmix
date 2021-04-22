/*
 * Copyright 2021 Haulmont.
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

public interface EntityReindexer {

    /**
     * Sends all instances of all index-configured entities to indexing queue.
     * <p>Indexing queue will be cleared and indices will be recreated.
     *
     * @return amount of enqueued instances
     */
    int enqueueReindexAll();

    /**
     * Sends all instance of provided index-configured entity to indexing queue.
     * <p>All items related to this entity will be deleted from indexing queue
     * and index for this entity will be recreated.
     *
     * @param entityName entity name
     * @return amount of enqueued instances
     */
    int enqueueReindexAll(String entityName);
}
