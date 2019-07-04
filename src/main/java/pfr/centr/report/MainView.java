package pfr.centr.report;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import pfr.centr.report.components.DataContainer;
import pfr.centr.report.models.ModeDataView;
import pfr.centr.report.models.UserInfo;
import pfr.centr.report.models.UserLogined;

@Route("")
@StyleSheet("styles/styles.css") // Relative to Servlet URL
public class MainView extends VerticalLayout implements BeforeEnterObserver, RouterLayout {

    @Autowired
    JdbcTemplate jdbcTemplate;
    public MainView() {

        //CONTAINER
        setSizeFull();
        setMargin(false);
        setSpacing(false);
        setPadding(false);
        add(BuildHeader());
        add(ReportMenu());
        add(BuildWorkspace());
    }

    private Component ReportMenu() {
        HorizontalLayout menuBlock = new HorizontalLayout();
        Button bNewReport = new Button();
        bNewReport.setIcon(new Icon(VaadinIcon.PLUS));
        bNewReport.setText("Новый отчет");
        Button bEditReport = new Button();
        bEditReport.setIcon(new Icon(VaadinIcon.EDIT));
        bEditReport.setText("Редактировать");
        menuBlock.addClassName("reportmenu");
        menuBlock.add(bNewReport,bEditReport);
        return menuBlock;
    }


    private Component BuildHeader(){
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
        return reportHeader;
    }

    private Component BuildWorkspace(){
        VerticalLayout workspace = new VerticalLayout();
        workspace.addClassName("workspace");
        workspace.setSizeFull();
        workspace.add(new DataContainer(ModeDataView.GRID));
        return workspace;
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
