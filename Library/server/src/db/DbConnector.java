package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnector {
    private Connection connection = null;
    private Statement statement = null;

    public void connectToDB() {
        try {

            this.connection = DriverManager.getConnection("jdbc:mysql://localhost/clinic?characterEncoding=utf8","root", "");
            //this.connection = DriverManager.getConnection("jdbc:D:\BSUIR\Курсач\PSP-CP-samples\PSP-CP-samples\PSP-CP-2\»Ó«Ñ¬Ô\clinic?characterEncoding=utf8","root", "");
            this.statement = connection.createStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }
}
