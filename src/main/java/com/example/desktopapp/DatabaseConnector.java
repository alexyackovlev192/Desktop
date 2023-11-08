package com.example.desktopapp;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnector {
    private Connection connection;

    public DatabaseConnector() {
        try {
            String url = "jdbc:mysql://localhost:3306/db_hotel";
            String username = "root";
            String password = "5eyL_r7u";
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}