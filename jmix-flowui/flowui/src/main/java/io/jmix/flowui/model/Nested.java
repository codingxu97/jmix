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

package io.jmix.flowui.model;

/**
 * Interface to be implemented by containers that work with entities that are properties of other entities.
 */
public interface Nested {

    /**
     * Returns the container holding the master entity.
     */
    InstanceContainer<?> getMaster();

    /**
     * Returns the name of the master entity property.
     */
    String getProperty();
}
