package ${packageName};

import ${entity.fqn};
import ${module_basePackage}.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

<%if (classComment) {%>
${classComment}
<%}%>@Route(value = "${route}", layout = MainView.class)
@UiController("${viewId}")
@UiDescriptor("${viewDescriptorName}.xml")
@LookupComponent("${tableId}")
@DialogMode(width = "50em", height = "37.5em")
public class ${viewControllerName} extends StandardListView<${entity.className}> {
}