package com.napier.coursework;

/*
 * SET08803 Coursework Application
 *
 */

import java.sql.*;
import java.util.Objects;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class App {

    // Connect to database
    static MySQLConnection mySQLConnection = new MySQLConnection();
    static Connection sqlConnect;

    public static void main(String[] args) {

        sqlConnect = MySQLConnection.connect();

        SpringApplication.run(App.class, args);

        System.out.println("Group6's website is now up and running. Waiting for http request...");

    }

    @RequestMapping(value = "/report",
            params = { "id", "grouping", "limit" },
            method = RequestMethod.GET)
    @ResponseBody
    public String getReport(@RequestParam(value = "id", defaultValue = "1") int ID, @RequestParam(value = "grouping", defaultValue = "") String grouping,
                            @RequestParam(value = "limit", defaultValue = "1") String limit) throws ClassNotFoundException, SQLException {

        System.out.println(ID + grouping + limit);

        // Create variable for the html report output
        String htmlOutput = "";

        System.out.println("ID is = "+ ID);
        System.out.println("grouping is = "+ grouping);
        System.out.println("limit is = "+ limit);

        try {

            // We create our handy report generator
            ReportEngine theReport = new ReportEngine();

            // In this mode, we expect variables to be passed - we can also create a loop here to cycle from Reports 1 to 32
            htmlOutput = theReport.generateReport(ID,grouping,limit,sqlConnect);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to produce report");

        }

        /*
         Produce HTML output in console - should be removed when testing complete
         System.out.println("--- HTML START ---");
         System.out.println(htmlOutput);
         System.out.println("--- HTML END ---");
        */

        return htmlOutput;
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String produceQueryHome()  throws ClassNotFoundException, SQLException {

        // Create variable for the html output
        String htmlOutput;

        htmlOutput = """
                <html>
                add home page and form to GET variables here
                <a href="/report?id=5&grouping=North America&limit=10">Test Test Test</a>
                </html>
                """;

        return htmlOutput;

    }

    public static void test() {
        System.out.println("Test in app class executed.");
    }
}