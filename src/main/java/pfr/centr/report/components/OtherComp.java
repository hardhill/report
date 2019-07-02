package pfr.centr.report.components;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;
import pfr.centr.report.MainView;

@Route(value = "comp", layout = MainView.class)
public class OtherComp extends Div {
    public OtherComp() {
        add("This component 'COMP'");
    }
}
