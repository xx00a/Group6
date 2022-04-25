package com.napier.coursework;

import java.sql.*;

public class MySQLConnection {

    private MySQLConnection() {}

    // connection with parameters
    public static Connection connect(String uri, String user, String password) {
        // Connection to the database
        Connection con = null;
        int retries = 3;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait a bit for db to start
                Thread.sleep(5000);
                // Connect to database
                con = DriverManager.getConnection(uri, user, password);
                System.out.println("Successfully connected");
                // Wait a bit
                Thread.sleep(1000);
                // Exit for loop
                return con;
            } catch (InterruptedException | SQLException e) {
                System.out.println("Failed to connect to database attempt " + i);
                System.out.println(e.getMessage());
            }
        }
        return con;
    }

    // Disconnect from the MySQL database.
    public static void disconnect(Connection connection) {
        if (connection != null) {
            try {
                // Close connection
                connection.close();
                System.out.println("Database connection closed.");
            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }


}
