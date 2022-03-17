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

    public static void main(String[] args) {

        SpringApplication.run(App.class, args);

    }

    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public String getReport(@RequestParam(value = "id") int ID, @RequestParam(value = "grouping") String grouping,
                            @RequestParam(value = "limit") int limit){
        return generateReport2(6,"Eastern Asia",0);
    }

    private String generateReport2(int argReport, String argVar, int limit) {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Hello Docker and Spring Boot!</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <h1>This is Group6's Docker-NGINX-SpringBoot application!</h1>\n" +
                "</body>\n" +
                "</html>";
    }

    private String generateReport(int argReport, String argVar, int limit) {
        /*
        for (int x = 0; x < args.length; x++)
        {
            switch (x) {
                case 0 -> argReport = Integer.parseInt(args[x]);
                case 1 -> argVar = args[x];
            }
        }//*/

        // Connect to database
        MySQLConnection mySQLConnection = new MySQLConnection();
        Connection sqlConnect = mySQLConnection.connect();

        ReportEngine theReport = null;

        try {

            System.out.println("\nReport ID: " + argReport + " (" + argVar + ")");
            // let's call our report generator
            theReport = new ReportEngine(argReport, argVar, sqlConnect);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to produce report");

        }

        // Disconnect from database
        mySQLConnection.disconnect(sqlConnect);

        //return Objects.isNull(theReport) ? null : theReport;
        return "Need HTML string here!!!";
    }

    public static void test()
  {
    System.out.println("Test in app class executed.");
  }
}