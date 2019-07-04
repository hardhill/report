package pfr.centr.report.components;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import pfr.centr.report.LoginView;

public class MainHeader extends HorizontalLayout {
    public MainHeader(){
        //button 'close'
        NativeButton bClose = new NativeButton();
        bClose.add(new Icon(VaadinIcon.CLOSE));
        bClose.getStyle().set("cursor","pointer");
        bClose.addClickListener(event->{
            UI.getCurrent().navigate(LoginView.class);
        });
        //logoSide with menu
        HorizontalLayout logoSide = new HorizontalLayout();
        Label logoTitle = new Label("REPORTmaster");
        logoTitle.addClassName("logoside");
        Anchor link1 = new Anchor("", "Отчеты");
        Anchor link2 = new Anchor("price", "Прайс");
        Anchor link3 = new Anchor("typerep", "Тип отчета");
        link1.addClassName("menulink");
        link2.addClassName("menulink");
        link3.addClassName("menulink");
        logoSide.add(logoTitle,link1,link2,link3);

        HorizontalLayout reportHeader = new HorizontalLayout();
        reportHeader.setWidth("100%");
        reportHeader.setAlignItems(FlexComponent.Alignment.CENTER);
        reportHeader.getStyle().set("border-bottom","1px solid #d1dfd6");
        reportHeader.expand(logoSide);
        reportHeader.add(logoSide,bClose);
    }
}
