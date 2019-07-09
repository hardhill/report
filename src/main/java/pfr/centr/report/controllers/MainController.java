package pfr.centr.report.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import pfr.centr.report.models.InfocenterDAO;
import pfr.centr.report.models.ReportView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private InfocenterDAO infocenterDAO;

    public List<ReportView> GetAllReports(){
        List<ReportView> otvet = new ArrayList<>();
        infocenterDAO = infocenterDAO == null ? new InfocenterDAO(jdbcTemplate) : infocenterDAO;
        return otvet;
    }
}
