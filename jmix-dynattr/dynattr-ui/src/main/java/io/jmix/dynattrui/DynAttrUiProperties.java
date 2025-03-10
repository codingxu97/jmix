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

package io.jmix.dynattrui;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.context.properties.bind.DefaultValue;

@ConfigurationProperties("jmix.dynattrui")
@ConstructorBinding
public class DynAttrUiProperties {

    /**
     * Max columns count for DynamicAttributesPanel
     */
    int dynamicAttributesPanelMaxColumnsCount;

    /**
     * Max recalculation level for dynamic attributes
     */
    int maxRecalculationLevel;

    public DynAttrUiProperties(@DefaultValue("4") int dynamicAttributesPanelMaxCount,
                               @DefaultValue("10") int maxRecalculationLevel) {
        this.dynamicAttributesPanelMaxColumnsCount = dynamicAttributesPanelMaxCount;
        this.maxRecalculationLevel = maxRecalculationLevel;
    }

    /**
     * @see #dynamicAttributesPanelMaxColumnsCount
     */
    public int getDynamicAttributesPanelMaxColumnsCount() {
        return dynamicAttributesPanelMaxColumnsCount;
    }

    /**
     * @see #maxRecalculationLevel
     */
    public int getMaxRecalculationLevel() {
        return maxRecalculationLevel;
    }
}
