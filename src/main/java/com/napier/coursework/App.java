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

        for (int x = 0; x < args.length; x++)
        {
            switch (x) {
                case 0 -> argReport = Integer.parseInt(args[x]);
                case 1 -> argVar = args[x];
            }
        }

        // Connect to database
        MySQLConnection mySQLConnection = new MySQLConnection();
        Connection sqlConnect = mySQLConnection.connect();

        // let's call our report generator
        ReportEngine theReport = new ReportEngine(argReport, argVar, sqlConnect);

        // Disconnect from database
        mySQLConnection.disconnect(sqlConnect);

    }
  public static void test()
  {
    System.out.println("Test in app class executed.");
  }
}