package pfr.centr.report.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import org.springframework.beans.factory.annotation.Autowired;
import pfr.centr.report.controllers.MainController;
import pfr.centr.report.models.InfocenterDAO;
import pfr.centr.report.models.ModeDataView;
import pfr.centr.report.models.ReportView;

import java.util.ArrayList;
import java.util.List;


public class DataContainer extends Div {

    @Autowired
    InfocenterDAO infocenterDAO;

    public DataContainer(ModeDataView modeDataView) {
        setSizeFull();
        removeAll();
        switch (modeDataView){
            case NEW:
                add(NewReport());
                break;
            case DELETE:
                add(EditReport());
                break;
            case EDIT:
                add(EditReport());
                break;
            default:
                add(GridReport());
        }
    }

    private Component GridReport() {
        Grid<ReportView> grid = new Grid<>(ReportView.class);
        List<ReportView> data = new ArrayList<>();
        MainController mainController = new MainController();
        data = mainController.GetAllReports();
        grid.setItems(data);
        return grid;
    }

    private Component EditReport() {
        return null;
    }

    private Component NewReport() {
        return null;
    }
}
