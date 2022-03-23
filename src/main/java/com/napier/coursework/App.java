package com.napier.coursework;

/*
 * SET08803 Coursework Application
 *
 */

import java.sql.*;
import java.util.Objects;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class App {

    static MySQLConnection mySQLConnection;
    static Connection sqlConnect;

    public static void main(String[] args) {

        // Connect to database
        mySQLConnection = new MySQLConnection();
        sqlConnect = mySQLConnection.connect();

        SpringApplication.run(App.class, args);

        System.out.println("Group6's website is now up and running. Waiting for http request...");

    }

    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public String getReport(@RequestParam(value = "id") int ID, @RequestParam(value = "grouping") String grouping,
                            @RequestParam(value = "limit") String limit) throws ClassNotFoundException, SQLException{

        // Create variable for the html report output
        String htmlOutput = "";

        try {

            // We create our handy report generator
            ReportEngine theReport = new ReportEngine();

            // In this mode, we expect variables to be passed - we can also create a loop here to cycle from Reports 1 to 32
            htmlOutput = theReport.generateReport(ID,grouping,limit,sqlConnect);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to produce report");

        }

        return htmlOutput;
    }

  public static void test()
  {
    System.out.println("Test in app class executed.");
  }

}