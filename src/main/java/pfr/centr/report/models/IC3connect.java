package pfr.centr.report.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class IC3connect {




    private static IC3connect ourInstance = new IC3connect();
    private Connection connection = null;
    private Statement statement;


    private IC3connect() {

        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            connection= DriverManager.getConnection(GetConnString(),GetUsernane(),GetPassword());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static IC3connect getInstance() {
        return ourInstance;
    }

    private String GetConnString(){
        return "jdbc:mysql://localhost:3306/infocenter3?useSSL=false&serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8";
    }

    private String GetUsernane(){
        return "icadmin";
    }

    private String GetPassword(){
        return "Inf0Center";
    }

    public Statement Statement() {
        try {
            statement =connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }
}
