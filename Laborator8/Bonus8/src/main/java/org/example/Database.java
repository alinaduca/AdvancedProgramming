package org.example;

import java.sql.*;
import org.apache.commons.dbcp2.BasicDataSource;

public class Database {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    //or jdbc:oracle:thin:@[HOST][:PORT]:SID
    private static final String USER = "system";
    private static final String PASSWORD = "1234";
    private static Connection connection = null;
    private static final BasicDataSource dataSource;
    private Database() {}

    static {
        dataSource = new BasicDataSource();
        dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        dataSource.setUrl(URL);
        dataSource.setUsername(USER);
        dataSource.setPassword(PASSWORD);
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
    private static void createConnection() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
}