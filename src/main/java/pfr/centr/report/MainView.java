package pfr.centr.report;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import pfr.centr.report.models.UserInfo;
import pfr.centr.report.models.UserLogined;

@Route("")
public class MainView extends Div implements BeforeEnterObserver, RouterLayout {


    public MainView() {
        add(BuildTopMenu());
        add(ReportMenu());
    }

    private Component ReportMenu() {
        HorizontalLayout menuBlock = new HorizontalLayout();
        menuBlock.setMargin(false);

        Button bNewReport = new Button();
        bNewReport.getStyle().set("pading","0px 20px");
        bNewReport.setIcon(new Icon(VaadinIcon.PLUS));
        bNewReport.setText("Новый отчет");
        Button bEditReport = new Button();
        bEditReport.setIcon(new Icon(VaadinIcon.EDIT));
        bEditReport.setText("Редактировать");
        menuBlock.setWidthFull();
        menuBlock.add(bNewReport,bEditReport);
        return menuBlock;
    }


    private Component BuildTopMenu(){
        NativeButton bClose = new NativeButton();
        bClose.add(new Icon(VaadinIcon.CLOSE));
        bClose.getStyle().set("cursor","pointer");
        bClose.addClickListener(event->{
            UI.getCurrent().navigate(LoginView.class);
        });
        HorizontalLayout logoSide = new HorizontalLayout();
        Label logoTitle = new Label("REPORTmaster");
        logoTitle.setWidth("100px");
        logoTitle.getStyle().set("margin","0px 25px");

        Anchor link = new Anchor("", "Отчеты");

        logoSide.add(logoTitle,link);
        HorizontalLayout menuBlock = new HorizontalLayout();
        menuBlock.setSpacing(false);
        menuBlock.setPadding(false);
        menuBlock.setMargin(false);
        menuBlock.setWidthFull();
        menuBlock.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
        menuBlock.setAlignItems(FlexComponent.Alignment.CENTER);
        menuBlock.getStyle().set("border-bottom","1px solid #d1dfd6");
        menuBlock.add(logoSide,bClose);
        return menuBlock;
    }
    private boolean validEnter(){
        boolean otvet = false;
        UserLogined userLogined = (UserLogined) UI.getCurrent().getSession().getAttribute("user");
        UserInfo userinfo = UserInfo.getInstance();
        otvet = (userLogined!=null)&&(userinfo.existsUser(userLogined));
        return otvet;
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnter) {
            if(!validEnter()){
                beforeEnter.forwardTo(LoginView.class);
            }
    }
}
