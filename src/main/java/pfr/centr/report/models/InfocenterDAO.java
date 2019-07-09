package pfr.centr.report.models;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Component
public class InfocenterDAO {


    JdbcTemplate jdbcTemplate;

    DateTimeFormatter dateformatter;

    public InfocenterDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        dateformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }

    public List<ReportView> GetAllReports(){
        List<ReportView> otvet = new ArrayList<>();
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet("select * from v_report");
        while (sqlRowSet.next()){
            ReportView reportView = new ReportView();
            reportView.setId(sqlRowSet.getLong("Id_report"));
            reportView.setDt(sqlRowSet.getDate("date_rep"));
            reportView.setUser(sqlRowSet.getString("fio"));

        }
        return otvet;
    }
}
