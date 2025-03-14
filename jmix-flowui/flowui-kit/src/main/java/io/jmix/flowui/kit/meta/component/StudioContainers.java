package io.jmix.flowui.kit.meta.component;

import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.tabs.Tabs;
import io.jmix.flowui.kit.meta.StudioComponent;
import io.jmix.flowui.kit.meta.StudioUiKit;
import io.jmix.flowui.kit.meta.StudioProperty;
import io.jmix.flowui.kit.meta.StudioPropertyType;

@StudioUiKit
public interface StudioContainers {

    @StudioComponent(
            name = "Details",
            classFqn = "com.vaadin.flow.component.details.Details",
            category = "Containers",
            xmlElement = "details",
            icon = "io/jmix/flowui/kit/meta/icon/container/details.svg",
            properties = {
                    @StudioProperty(xmlAttribute = "className", type = StudioPropertyType.VALUES_LIST),
                    @StudioProperty(xmlAttribute = "enabled", type = StudioPropertyType.BOOLEAN,
                            defaultValue = "true"),
                    @StudioProperty(xmlAttribute = "height", type = StudioPropertyType.SIZE),
                    @StudioProperty(xmlAttribute = "id", type = StudioPropertyType.COMPONENT_ID),
                    @StudioProperty(xmlAttribute = "maxHeight", type = StudioPropertyType.SIZE),
                    @StudioProperty(xmlAttribute = "maxWidth", type = StudioPropertyType.SIZE),
                    @StudioProperty(xmlAttribute = "minHeight", type = StudioPropertyType.SIZE),
                    @StudioProperty(xmlAttribute = "minWidth", type = StudioPropertyType.SIZE),
                    @StudioProperty(xmlAttribute = "summaryText", type = StudioPropertyType.LOCALIZED_STRING),
                    @StudioProperty(xmlAttribute = "themeName", type = StudioPropertyType.VALUES_LIST,
                            options = {"filled", "reverse", "small"}),
                    @StudioProperty(xmlAttribute = "visible", type = StudioPropertyType.BOOLEAN,
                            defaultValue = "true"),
                    @StudioProperty(xmlAttribute = "width", type = StudioPropertyType.SIZE)
            }
    )
    Details details();

    @StudioComponent(
            name = "HBox",
            classFqn = "com.vaadin.flow.component.orderedlayout.HorizontalLayout",
            category = "Containers",
            xmlElement = "hbox",
            icon = "io/jmix/flowui/kit/meta/icon/container/hbox.svg",
            properties = {
                    @StudioProperty(xmlAttribute = "alignItems", type = StudioPropertyType.ENUMERATION,
                            classFqn = "com.vaadin.flow.component.orderedlayout.FlexComponent$Alignment",
                            defaultValue = "STRETCH",
                            options = {"START", "END", "CENTER", "STRETCH", "BASELINE", "AUTO"}),
                    @StudioProperty(xmlAttribute = "boxSizing", type = StudioPropertyType.ENUMERATION,
                            classFqn = "com.vaadin.flow.component.orderedlayout.BoxSizing", defaultValue = "UNDEFINED",
                            options = {"UNDEFINED", "CONTENT_BOX", "BORDER_BOX"}),
                    @StudioProperty(xmlAttribute = "className", type = StudioPropertyType.VALUES_LIST),
                    @StudioProperty(xmlAttribute = "enabled", type = StudioPropertyType.BOOLEAN,
                            defaultValue = "true"),
                    @StudioProperty(xmlAttribute = "expand", type = StudioPropertyType.STRING),
                    @StudioProperty(xmlAttribute = "height", type = StudioPropertyType.SIZE),
                    @StudioProperty(xmlAttribute = "id", type = StudioPropertyType.COMPONENT_ID),
                    @StudioProperty(xmlAttribute = "justifyContent", type = StudioPropertyType.ENUMERATION,
                            classFqn = "com.vaadin.flow.component.orderedlayout.FlexComponent$JustifyContentMode",
                            setMethod = "setJustifyContentMode", defaultValue = "START",
                            options = {"START", "END", "CENTER", "BETWEEN", "AROUND", "EVENLY"}),
                    @StudioProperty(xmlAttribute = "margin", type = StudioPropertyType.BOOLEAN,
                            defaultValue = "false"),
                    @StudioProperty(xmlAttribute = "maxHeight", type = StudioPropertyType.SIZE),
                    @StudioProperty(xmlAttribute = "maxWidth", type = StudioPropertyType.SIZE),
                    @StudioProperty(xmlAttribute = "minHeight", type = StudioPropertyType.SIZE),
                    @StudioProperty(xmlAttribute = "minWidth", type = StudioPropertyType.SIZE),
                    @StudioProperty(xmlAttribute = "padding", type = StudioPropertyType.BOOLEAN,
                            defaultValue = "false"),
                    @StudioProperty(xmlAttribute = "spacing", type = StudioPropertyType.BOOLEAN,
                            defaultValue = "true"),
                    @StudioProperty(xmlAttribute = "visible", type = StudioPropertyType.BOOLEAN,
                            defaultValue = "true"),
                    @StudioProperty(xmlAttribute = "width", type = StudioPropertyType.SIZE)
            }
    )
    HorizontalLayout hbox();

    @StudioComponent(
            name = "VBox",
            classFqn = "com.vaadin.flow.component.orderedlayout.VerticalLayout",
            category = "Containers",
            xmlElement = "vbox",
            icon = "io/jmix/flowui/kit/meta/icon/container/vbox.svg",
            properties = {
                    @StudioProperty(xmlAttribute = "alignItems", type = StudioPropertyType.ENUMERATION,
                            classFqn = "com.vaadin.flow.component.orderedlayout.FlexComponent$Alignment",
                            defaultValue = "STRETCH",
                            options = {"START", "END", "CENTER", "STRETCH", "BASELINE", "AUTO"}),
                    @StudioProperty(xmlAttribute = "boxSizing", type = StudioPropertyType.ENUMERATION,
                            classFqn = "com.vaadin.flow.component.orderedlayout.BoxSizing", defaultValue = "UNDEFINED",
                            options = {"UNDEFINED", "CONTENT_BOX", "BORDER_BOX"}),
                    @StudioProperty(xmlAttribute = "className", type = StudioPropertyType.VALUES_LIST),
                    @StudioProperty(xmlAttribute = "enabled", type = StudioPropertyType.BOOLEAN,
                            defaultValue = "true"),
                    @StudioProperty(xmlAttribute = "expand", type = StudioPropertyType.STRING),
                    @StudioProperty(xmlAttribute = "height", type = StudioPropertyType.SIZE),
                    @StudioProperty(xmlAttribute = "id", type = StudioPropertyType.COMPONENT_ID),
                    @StudioProperty(xmlAttribute = "justifyContent", type = StudioPropertyType.ENUMERATION,
                            classFqn = "com.vaadin.flow.component.orderedlayout.FlexComponent$JustifyContentMode",
                            setMethod = "setJustifyContentMode", defaultValue = "START",
                            options = {"START", "END", "CENTER", "BETWEEN", "AROUND", "EVENLY"}),
                    @StudioProperty(xmlAttribute = "margin", type = StudioPropertyType.BOOLEAN,
                            defaultValue = "false"),
                    @StudioProperty(xmlAttribute = "maxHeight", type = StudioPropertyType.SIZE),
                    @StudioProperty(xmlAttribute = "maxWidth", type = StudioPropertyType.SIZE),
                    @StudioProperty(xmlAttribute = "minHeight", type = StudioPropertyType.SIZE),
                    @StudioProperty(xmlAttribute = "minWidth", type = StudioPropertyType.SIZE),
                    @StudioProperty(xmlAttribute = "padding", type = StudioPropertyType.BOOLEAN,
                            defaultValue = "true"),
                    @StudioProperty(xmlAttribute = "spacing", type = StudioPropertyType.BOOLEAN,
                            defaultValue = "true"),
                    @StudioProperty(xmlAttribute = "visible", type = StudioPropertyType.BOOLEAN,
                            defaultValue = "true"),
                    @StudioProperty(xmlAttribute = "width", type = StudioPropertyType.SIZE, defaultValue = "100%")
            }
    )
    VerticalLayout vbox();

    @StudioComponent(
            name = "Scroller",
            classFqn = "com.vaadin.flow.component.orderedlayout.Scroller",
            category = "Containers",
            xmlElement = "scroller",
            icon = "io/jmix/flowui/kit/meta/icon/container/scroller.svg",
            properties = {
                    @StudioProperty(xmlAttribute = "className", type = StudioPropertyType.VALUES_LIST),
                    @StudioProperty(xmlAttribute = "colspan", type = StudioPropertyType.INTEGER),
                    @StudioProperty(xmlAttribute = "height", type = StudioPropertyType.SIZE),
                    @StudioProperty(xmlAttribute = "id", type = StudioPropertyType.COMPONENT_ID),
                    @StudioProperty(xmlAttribute = "maxHeight", type = StudioPropertyType.SIZE),
                    @StudioProperty(xmlAttribute = "maxWidth", type = StudioPropertyType.SIZE),
                    @StudioProperty(xmlAttribute = "minHeight", type = StudioPropertyType.SIZE),
                    @StudioProperty(xmlAttribute = "minWidth", type = StudioPropertyType.SIZE),
                    @StudioProperty(xmlAttribute = "scrollBarsDirection", type = StudioPropertyType.ENUMERATION,
                            classFqn = "com.vaadin.flow.component.orderedlayout.Scroller$ScrollDirection",
                            setMethod = "setScrollDirection", defaultValue = "VERTICAL",
                            options = {"VERTICAL", "HORIZONTAL", "BOTH", "NONE"}),
                    @StudioProperty(xmlAttribute = "visible", type = StudioPropertyType.BOOLEAN,
                            defaultValue = "true"),
                    @StudioProperty(xmlAttribute = "width", type = StudioPropertyType.SIZE, defaultValue = "100%")
            }
    )
    Scroller scroller();

    @StudioComponent(
            name = "Split",
            classFqn = "com.vaadin.flow.component.splitlayout.SplitLayout",
            category = "Containers",
            xmlElement = "split",
            icon = "io/jmix/flowui/kit/meta/icon/container/split.svg",
            properties = {
                    @StudioProperty(xmlAttribute = "className", type = StudioPropertyType.VALUES_LIST),
                    @StudioProperty(xmlAttribute = "colspan", type = StudioPropertyType.INTEGER),
                    @StudioProperty(xmlAttribute = "height", type = StudioPropertyType.SIZE),
                    @StudioProperty(xmlAttribute = "id", type = StudioPropertyType.COMPONENT_ID),
                    @StudioProperty(xmlAttribute = "maxHeight", type = StudioPropertyType.SIZE),
                    @StudioProperty(xmlAttribute = "maxWidth", type = StudioPropertyType.SIZE),
                    @StudioProperty(xmlAttribute = "minHeight", type = StudioPropertyType.SIZE),
                    @StudioProperty(xmlAttribute = "minWidth", type = StudioPropertyType.SIZE),
                    @StudioProperty(xmlAttribute = "orientation", type = StudioPropertyType.ENUMERATION,
                            classFqn = "com.vaadin.flow.component.splitlayout.SplitLayout$Orientation",
                            setMethod = "setOrientation", defaultValue = "HORIZONTAL",
                            options = {"VERTICAL", "HORIZONTAL"}),
                    @StudioProperty(xmlAttribute = "splitterPosition", type = StudioPropertyType.INTEGER),
                    @StudioProperty(xmlAttribute = "themeName", type = StudioPropertyType.VALUES_LIST,
                            options = {"small", "minimal"}),
                    @StudioProperty(xmlAttribute = "visible", type = StudioPropertyType.BOOLEAN,
                            defaultValue = "true"),
                    @StudioProperty(xmlAttribute = "width", type = StudioPropertyType.SIZE, defaultValue = "100%")
            }
    )
    SplitLayout splitLayout();

    @StudioComponent(
            name = "Tabs",
            classFqn = "com.vaadin.flow.component.tabs.Tabs",
            category = "Containers",
            xmlElement = "tabs",
            icon = "io/jmix/flowui/kit/meta/icon/container/tabs.svg",
            properties = {
                    @StudioProperty(xmlAttribute = "className", type = StudioPropertyType.VALUES_LIST),
                    @StudioProperty(xmlAttribute = "colspan", type = StudioPropertyType.INTEGER),
                    @StudioProperty(xmlAttribute = "height", type = StudioPropertyType.SIZE),
                    @StudioProperty(xmlAttribute = "id", type = StudioPropertyType.COMPONENT_ID),
                    @StudioProperty(xmlAttribute = "maxHeight", type = StudioPropertyType.SIZE),
                    @StudioProperty(xmlAttribute = "maxWidth", type = StudioPropertyType.SIZE),
                    @StudioProperty(xmlAttribute = "minHeight", type = StudioPropertyType.SIZE),
                    @StudioProperty(xmlAttribute = "minWidth", type = StudioPropertyType.SIZE),
                    @StudioProperty(xmlAttribute = "orientation", type = StudioPropertyType.ENUMERATION,
                            classFqn = "com.vaadin.flow.component.tabs.Tabs$Orientation",
                            setMethod = "setOrientation", defaultValue = "HORIZONTAL",
                            options = {"VERTICAL", "HORIZONTAL"}),
                    @StudioProperty(xmlAttribute = "themeName", type = StudioPropertyType.VALUES_LIST,
                            options = {"icon-on-top", "centered", "small", "minimal",
                                    "hide-scroll-buttons", "equal-width-tabs", "fixed"}),
                    @StudioProperty(xmlAttribute = "visible", type = StudioPropertyType.BOOLEAN,
                            defaultValue = "true"),
                    @StudioProperty(xmlAttribute = "width", type = StudioPropertyType.SIZE, defaultValue = "100%")
            }
    )
    Tabs tabs();

    @StudioComponent(
            name = "Accordion",
            classFqn = "com.vaadin.flow.component.accordion.Accordion",
            category = "Containers",
            xmlElement = "accordion",
            icon = "io/jmix/flowui/kit/meta/icon/container/accordion.svg",
            properties = {
                    @StudioProperty(xmlAttribute = "className", type = StudioPropertyType.VALUES_LIST),
                    @StudioProperty(xmlAttribute = "colspan", type = StudioPropertyType.INTEGER),
                    @StudioProperty(xmlAttribute = "height", type = StudioPropertyType.SIZE),
                    @StudioProperty(xmlAttribute = "id", type = StudioPropertyType.COMPONENT_ID),
                    @StudioProperty(xmlAttribute = "maxHeight", type = StudioPropertyType.SIZE),
                    @StudioProperty(xmlAttribute = "maxWidth", type = StudioPropertyType.SIZE),
                    @StudioProperty(xmlAttribute = "minHeight", type = StudioPropertyType.SIZE),
                    @StudioProperty(xmlAttribute = "minWidth", type = StudioPropertyType.SIZE),
                    @StudioProperty(xmlAttribute = "visible", type = StudioPropertyType.BOOLEAN,
                            defaultValue = "true"),
                    @StudioProperty(xmlAttribute = "width", type = StudioPropertyType.SIZE, defaultValue = "100%")
            }
    )
    Accordion accordion();

    @StudioComponent(
            name = "FormLayout",
            classFqn = "com.vaadin.flow.component.formlayout.FormLayout",
            category = "Containers",
            xmlElement = "formLayout",
            icon = "io/jmix/flowui/kit/meta/icon/container/formLayout.svg",
            properties = {
                    @StudioProperty(xmlAttribute = "className", type = StudioPropertyType.VALUES_LIST),
                    @StudioProperty(xmlAttribute = "colspan", type = StudioPropertyType.INTEGER),
                    @StudioProperty(xmlAttribute = "dataContainer", type = StudioPropertyType.STRING),
                    @StudioProperty(xmlAttribute = "enabled", type = StudioPropertyType.BOOLEAN,
                            defaultValue = "true"),
                    @StudioProperty(xmlAttribute = "height", type = StudioPropertyType.SIZE),
                    @StudioProperty(xmlAttribute = "id", type = StudioPropertyType.COMPONENT_ID),
                        @StudioProperty(xmlAttribute = "labelsPosition", type = StudioPropertyType.ENUMERATION,
                        classFqn = "com.vaadin.flow.component.formlayout.FormLayout$ResponsiveStep$LabelsPosition",
                                options = {"ASIDE", "TOP"}),
                    @StudioProperty(xmlAttribute = "maxHeight", type = StudioPropertyType.SIZE),
                    @StudioProperty(xmlAttribute = "maxWidth", type = StudioPropertyType.SIZE),
                    @StudioProperty(xmlAttribute = "minHeight", type = StudioPropertyType.SIZE),
                    @StudioProperty(xmlAttribute = "minWidth", type = StudioPropertyType.SIZE),
                    @StudioProperty(xmlAttribute = "visible", type = StudioPropertyType.BOOLEAN,
                            defaultValue = "true"),
                    @StudioProperty(xmlAttribute = "width", type = StudioPropertyType.SIZE, defaultValue = "100%")
            }
    )
    FormLayout formLayout();
}
