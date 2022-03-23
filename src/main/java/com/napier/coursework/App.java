package com.napier.coursework;

/*
 * SET08803 Coursework Application
 *
 */

import java.sql.*;
import java.util.*;

public class App {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        // Global variables ***** default to ("" or 0) when testing is complete - to be passed from NGINX web application *****
        String htmlOutput = "";
        int argReport = 5;
        String argVar = "North America";
        String argLimit = "5";

        // Connect to database
        MySQLConnection mySQLConnection = new MySQLConnection();
        Connection sqlConnect = mySQLConnection.connect();

        try {

            System.out.println("\nReport ID: " + argReport + " (" + argVar + ")");

            // We create our handy report generator
            ReportEngine theReport = new ReportEngine();

            // In this mode, we expect variables to be passed - we can also create a loop here to cycle from Reports 1 to 32
            htmlOutput = theReport.generateReport(argReport, argVar, argLimit, sqlConnect);

            // @RAY you can pass your data here ---->

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to produce report");

        }

        // Produce HTML output in console - should be removed when testing complete
        System.out.println("--- HTML START ---");
        System.out.println(htmlOutput);
        System.out.println("--- HTML END ---");

        // Disconnect from database
        mySQLConnection.disconnect(sqlConnect);

    }

  public static void test()
  {
    System.out.println("Test in app class executed.");
  }
}