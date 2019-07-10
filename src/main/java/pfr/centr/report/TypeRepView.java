package pfr.centr.report;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
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
import java.util.stream.Stream;

@Route("typerep")
@StyleSheet("styles/styles.css") // Relative to Servlet URL
public class TypeRepView extends VerticalLayout implements BeforeEnterObserver {

    private InfocenterDAO infocenterDAO;
    Grid<TypeReport> grid;

    public TypeRepView() {
        infocenterDAO = new InfocenterDAO();
        grid = new Grid<>();
        grid.setMinWidth("420px"); grid.setMaxWidth("640px");
        grid.setMaxHeight("380px");
        grid.addColumn(TypeReport::getId).setHeader("#").setWidth("20px");
        grid.addColumn(TypeReport::getTypereport).setHeader("Наименование вида отчета").setWidth("400px");
        List<TypeReport> data  = new ArrayList<>();
        try {
            data  = infocenterDAO.GetAllTypeReports(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        grid.setItems(data);
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
        //родительский контейнер
        VerticalLayout workspaceGrid = new VerticalLayout();
        VerticalLayout pnlGrid = new VerticalLayout();
        Button bNewTypeReport = new Button();
        bNewTypeReport.setIcon(new Icon(VaadinIcon.PLUS));
        bNewTypeReport.setText("Новый тип отчета");
        bNewTypeReport.addClickListener(e->{
            Dialog newTypeReportDialog = new Dialog();
            newTypeReportDialog.setCloseOnOutsideClick(false);
            Text label = new Text("Введите название отчета");
            TextField edtType = new TextField("");
            edtType.setWidth("180px");
            Button bOk = new Button("Сохранить");
            bOk.addClickListener(event->{
                //сохранить значение в БД
                if(infocenterDAO.NewTypeReport(edtType.getValue())){
                    try {
                        grid.setItems(new ArrayList<TypeReport>());
                        grid.setItems((Stream<TypeReport>) infocenterDAO.GetAllReports());
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
                newTypeReportDialog.close();
            });
            Button bCancel = new Button("Отмена");
            bCancel.addClickListener(event->{
                newTypeReportDialog.close();
            });
            newTypeReportDialog.add(new VerticalLayout(label,edtType),new HorizontalLayout(bOk,bCancel));
            newTypeReportDialog.open();
        });
        pnlGrid.add(grid);
        pnlGrid.add(bNewTypeReport);
       //добавить в родительский/корневой контейнер
        workspaceGrid.add(pnlGrid);
        return workspaceGrid;
    }

    private Component TableGrid() {

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
