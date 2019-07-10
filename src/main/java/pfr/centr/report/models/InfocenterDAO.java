package pfr.centr.report.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;



public class InfocenterDAO {
    Statement statement;
    DateTimeFormatter dateformatter;
    public InfocenterDAO() {
        statement = IC3connect.getInstance().Statement();
        dateformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }

    public List<ReportView> GetAllReports() throws SQLException {
        List<ReportView> otvet = new ArrayList<>();
        ResultSet sqlRowSet = null;
        try {
            sqlRowSet = statement.executeQuery ("select * from v_report");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        while (sqlRowSet.next()){
            ReportView reportView = new ReportView();
            reportView.setId(sqlRowSet.getLong("Id_report"));
            reportView.setDt(sqlRowSet.getDate("date_rep"));
            reportView.setUser(sqlRowSet.getString("fio"));
            otvet.add(reportView);
        }
        //statement.cancel();
        return otvet;
    }

    public List<TypeReport> GetAllTypeReports(int id) throws SQLException {
        List<TypeReport> otvet = new ArrayList<>();
        ResultSet result = null;

        String sql = "select * from report_type";
        if (id != 0) {
            sql = "select * from report_type where id=" + String.valueOf(id);
        }
        try {
            result = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        while (result.next()) {
            TypeReport typeReport = new TypeReport();
            typeReport.setId(result.getLong("id_report"));
            typeReport.setTypereport(result.getString("name"));
            otvet.add(typeReport);
        }
        return otvet;
    }

    public boolean NewTypeReport(String value) {
        boolean result = false;
        String sql = "INSERT INTO report_type (name) VALUES ('"+value+"')";
        try {
            result = statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
