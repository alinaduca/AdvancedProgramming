package org.example;

import java.sql.*;

public class Database {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    //or jdbc:oracle:thin:@[HOST][:PORT]:SID
    private static final String USER = "system";
    private static final String PASSWORD = "1234";
    private static Connection connection = null;
    private Database() {}
    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
        return connection;
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
        if(connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
}