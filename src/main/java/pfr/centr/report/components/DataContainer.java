package pfr.centr.report.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import pfr.centr.report.models.InfocenterDAO;
import pfr.centr.report.models.ModeDataView;
import pfr.centr.report.models.ReportView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DataContainer extends Div {



    InfocenterDAO infocenterDAO;

    public DataContainer(ModeDataView modeDataView) {
        infocenterDAO = new InfocenterDAO();
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
        try {
            data = infocenterDAO.GetAllReports();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
