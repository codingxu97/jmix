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

package io.jmix.ui.component;

import io.jmix.ui.meta.PropertyType;
import io.jmix.ui.meta.StudioComponent;
import io.jmix.ui.meta.StudioProperty;

import javax.annotation.Nullable;

/**
 * Component shows if CapsLock key is enabled when user inputs password.
 */
@StudioComponent(
        caption = "CapsLockIndicator",
        xmlElement = "capsLockIndicator",
        category = "Components",
        icon = "io/jmix/ui/icon/component/capsLockIndicator.svg",
        documentationURL = "https://docs.jmix.io/jmix/%VERSION%/ui/vcl/components/caps-lock-indicator.html",
        unsupportedProperties = {"enable", "responsive"}
)
public interface CapsLockIndicator extends Component {
    String NAME = "capsLockIndicator";

    /**
     * Sets the message that will be shown when caps lock key is active.
     *
     * @param capsLockOnMessage text message
     */
    @StudioProperty(type = PropertyType.LOCALIZED_STRING)
    void setCapsLockOnMessage(@Nullable String capsLockOnMessage);

    /**
     * @return message that is shown when caps lock key is active
     */
    @Nullable
    String getCapsLockOnMessage();

    /**
     * Sets the message that will be shown when caps lock key is not active.
     *
     * @param capsLockOffMessage text message
     */
    @StudioProperty(type = PropertyType.LOCALIZED_STRING)
    void setCapsLockOffMessage(@Nullable String capsLockOffMessage);

    /**
     * @return message that is shown when caps lock key is not active
     */
    @Nullable
    String getCapsLockOffMessage();
}
