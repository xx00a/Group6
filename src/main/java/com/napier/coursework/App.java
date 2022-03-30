package com.napier.coursework;

/*
 * SET08803 Coursework Application
 * Current working version: March 24, 2022
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
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Group 6 Coursework</title>
</head>
<style>
    table {
        border-collapse: collapse;
        border: 1px solid;
        font-family: sans-serif;
    }

    th, td {
        border: 1px solid;
        padding: 10px 10px;
        text-align: center;
    }

    body {
        background-color: #D3D3D3;
        position: relative;
        text-align: center;
        font-family: Arial,serif; size: 11px;
    }

</style>
<body>
<h1>SET08803 2021-2 TR2 001 - Software Engineering Methods</h1>
<h2>Coursework - Part 2 - <a href="https://github.com/xx00a/Group6">Group 6</a>
</h2>
<p>Please select report number that you would like to generate:</p>
<div class="selectIDdropdown">
  <select id="reportID">
        <option value="1">Report 1</option>
        <option value="2">Report 2</option>
        <option value="3">Report 3</option>
        <option value="4">Report 4</option>
        <option value="5">Report 5</option>
        <option value="6">Report 6</option>
        <option value="7">Report 7</option>
        <option value="8">Report 8</option>
        <option value="9">Report 9</option>
        <option value="10">Report 10</option>
        <option value="11">Report 11</option>
        <option value="12">Report 12</option>
        <option value="13">Report 13</option>
        <option value="14">Report 14</option>
        <option value="15">Report 15</option>
        <option value="16">Report 16</option>
        <option value="17">Report 17</option>
        <option value="18">Report 18</option>
        <option value="19">Report 19</option>
        <option value="20">Report 20</option>
        <option value="21">Report 21</option>
        <option value="22">Report 22</option>
        <option value="23">Report 23</option>
        <option value="24">Report 24</option>
        <option value="25">Report 25</option>
        <option value="26">Report 26</option>
        <option value="27">Report 27</option>
        <option value="28">Report 28</option>
        <option value="29">Report 29</option>
        <option value="30">Report 30</option>
        <option value="31">Report 31</option>
    </select>
</div>
<p>Please write a grouping you would like to use <i>(can be left blank)</i></p>

<div class="selectGrouping">
    <input id="grouping" type="text" placeholder="grouping...">
</div>
<p>
    Please enter a limit of results <i>(default is 1)</i>
</p>
<div class="selectLimit">
    <input id="limitOf" type="text" placeholder="number of results...">
</div>

<button onclick="submit()"> GENERATE REPORT</button>

<script>
    function submit() {
        var a = document.getElementById("reportID");
        var reportNumber = (a.options[a.selectedIndex].index) + 1;
        var groupingType = document.getElementById("grouping");
        var limitType = document.getElementById("limitOf");

        window.open("/report?id=" + reportNumber + "&grouping=" + groupingType.value + "&limit=" + limitType.value);
        //console.log("/report?id=" + reportNumber + "&grouping=" + groupingType.value + "&limit=" + limitType.value);


    }
</script>
</body>
</html>
                """;

        return htmlOutput;

    }

    public static void test() {
        System.out.println("Test in app class executed.");
    }
}