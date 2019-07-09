package pfr.centr.report;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import pfr.centr.report.models.InfocenterDAO;
import pfr.centr.report.models.TypeReport;
import pfr.centr.report.models.UserInfo;
import pfr.centr.report.models.UserLogined;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Route("typerep")
@StyleSheet("styles/styles.css") // Relative to Servlet URL
public class TypeRepView extends VerticalLayout implements BeforeEnterObserver {

    private InfocenterDAO infocenterDAO;
    public TypeRepView() {
        infocenterDAO = new InfocenterDAO();
        add(HeaderTRView(), GridTypeReport());
    }

    private Component HeaderTRView() {
        HorizontalLayout headerLine = new HorizontalLayout();
        headerLine.setPadding(false);
        headerLine.setAlignItems(Alignment.CENTER);
        Icon iconBack = new Icon(VaadinIcon.ARROW_BACKWARD);
        iconBack.setSize("18px");
        iconBack.setClassName("iconback");
        iconBack.addClickListener(e->{
            UI.getCurrent().navigate(MainView.class);
        });
        Label label = new Label("Типы отчетов"); label.setClassName("logoside");
        headerLine.setClassName("reportmenu");
        headerLine.getStyle().set("border-bottom","1px solid #d1dfd6");
        headerLine.add(iconBack, label);
        return headerLine;
    }

    private Component GridTypeReport(){
        HorizontalLayout pnlGrid = new HorizontalLayout();
        pnlGrid.setJustifyContentMode(JustifyContentMode.CENTER);
        pnlGrid.add(TableGrid());
        //родительский контейнер
        VerticalLayout workspaceGrid = new VerticalLayout();
        workspaceGrid.add(pnlGrid);
        return workspaceGrid;
    }

    private Component TableGrid() {
        Grid<TypeReport> grid = new Grid<>(TypeReport.class);
        List<TypeReport> items  = new ArrayList<>();
        try {
           items  = infocenterDAO.GetAllTypeReports(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        grid.setItems(items);
        return grid;
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if(!validEnter()){
            beforeEnterEvent.forwardTo(LoginView.class);
        }
    }

    private boolean validEnter() {
        boolean otvet = false;
        UserLogined userLogined = (UserLogined) UI.getCurrent().getSession().getAttribute("user");
        UserInfo userinfo = UserInfo.getInstance();
        otvet = (userLogined!=null)&&(userinfo.existsUser(userLogined));
        return otvet;
    }
}
