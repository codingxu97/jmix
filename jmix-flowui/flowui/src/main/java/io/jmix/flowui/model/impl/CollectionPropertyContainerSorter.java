/*
 * Copyright 2019 Haulmont.
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

package io.jmix.flowui.model.impl;

import io.jmix.flowui.model.CollectionPropertyContainer;
import org.springframework.beans.factory.BeanFactory;

import java.util.List;

/**
 * Standard implementation of sorting {@link CollectionPropertyContainer}s.
 */
public class CollectionPropertyContainerSorter extends BaseContainerSorter {

    public CollectionPropertyContainerSorter(CollectionPropertyContainer<?> container, BeanFactory beanFactory) {
        super(container, beanFactory);
    }

    @Override
    public CollectionPropertyContainer<?> getContainer() {
        return (CollectionPropertyContainer<?>) super.getContainer();
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    protected void setItemsToContainer(List<?> list) {
        getContainer().setDisconnectedItems(((List) list));
    }
}
