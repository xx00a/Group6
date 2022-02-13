package com.napier.coursework;

import java.sql.*;

public class App
{
    public static void main(String[] args) {

        // Connect to database
        MySQLConnection mySQLConnection = new MySQLConnection();

        Connection connection= mySQLConnection.connect();

        CityReport cityReport = new CityReport();
        // Display results
        cityReport.getCity(10, connection);

        // Disconnect from database
        mySQLConnection.disconnect(connection);
    }

}