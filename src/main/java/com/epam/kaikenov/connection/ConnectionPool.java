package com.epam.kaikenov.connection;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPool {

    private static final Logger log = Logger.getLogger(ConnectionPool.class);

    private static String USER = "admin";
    private static String PASSWORD = "admin";
    private static String URL = "jdbc:h2:tcp://localhost:9092/~/aircompany";
    private static Connection instanceConnection;

    public ConnectionPool() {
    }

    public static Connection getInstanceConnection() {
        if (instanceConnection == null) {

            try {
                Class.forName("org.h2.Driver");
                instanceConnection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                throw new RuntimeException("ConnectionPool error " + e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("Class not found " + e);
            }
        }
        return instanceConnection;
    }
}
