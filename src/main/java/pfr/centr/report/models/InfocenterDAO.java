package pfr.centr.report.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Component
public class InfocenterDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    DateTimeFormatter dateformatter;

    public InfocenterDAO() {

        dateformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }

    public List<TypeReportView> GetAllReports(){
        List<TypeReportView> otvet = new ArrayList<>();
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet("select * from v_report");
        while (sqlRowSet.next()){
            TypeReportView typeReportView = new TypeReportView();
            typeReportView.setId(sqlRowSet.getLong("Id_report"));
            typeReportView.setDt(sqlRowSet.getDate("date_rep"));
            typeReportView.setUser(sqlRowSet.getString("fio"));

        }
        return otvet;
    }
}
