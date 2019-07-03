package pfr.centr.report.components;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class Caption extends HorizontalLayout {

    public Caption(String txt,String width,JustifyContentMode justify) {
        getStyle().set("width",width);
        getStyle().set("margin","0px");
        if(justify!=null) {
            setJustifyContentMode(justify);
        }
        add(new Label(txt));
    }
}
