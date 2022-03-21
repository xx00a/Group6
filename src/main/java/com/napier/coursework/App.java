package com.napier.coursework;

/*
 * SET08803 Coursework Application
 *
 */

import java.sql.*;
import java.util.*;

public class App {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        // load arguments
        int argReport = 0;
        String argVar = "";
        String argLimit = "";

        for (int x = 0; x < args.length; x++)
        {
            switch (x) {
                case 0 -> argReport = Integer.parseInt(args[x]);
                case 1 -> argVar = args[x];
                case 2 -> argLimit = args[x];
            }
        }

        // Connect to database
        MySQLConnection mySQLConnection = new MySQLConnection();
        Connection sqlConnect = mySQLConnection.connect();

        try {

            System.out.println("\nReport ID: " + argReport + " (" + argVar + ")");

            // let's call our report generator
            ReportEngine theReport = new ReportEngine(argReport, argVar, argLimit, sqlConnect);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to produce report");

        }

        // Disconnect from database
        mySQLConnection.disconnect(sqlConnect);

    }
}