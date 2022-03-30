package com.napier.coursework;

/*
 * SET08803 Coursework Application
 * Current working version: March 30, 2022
 */

import java.sql.*;
import java.util.Objects;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;


// Set this class as the Spring Boot application and as the REST API controller
@SpringBootApplication
@RestController
public class App {

    // Connect to database
    static MySQLConnection mySQLConnection = new MySQLConnection();
    static Connection sqlConnect;

    public static void main(String[] args) {

        // Initialise the connection to the "world" database on the MySQL db server
        sqlConnect = MySQLConnection.connect();

        // Start our Spring Boot application
        SpringApplication.run(App.class, args);

        // Add a line in the console log to wait for a request to come through
        System.out.println("Group6's website is now up and running. Waiting for http request...");

    }

    // Coursework API where report generation request will go through and will be returned to the NGINX webserver
    @RequestMapping(value = "/report",
            params = { "id", "grouping", "limit" },
            method = RequestMethod.GET)
    @ResponseBody
    public String getReport(@RequestParam(value = "id", defaultValue = "1") int ID, @RequestParam(value = "grouping", defaultValue = "") String grouping,
                            @RequestParam(value = "limit", defaultValue = "1") String limit) throws ClassNotFoundException, SQLException {

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
                                       \s
                                th, td {
                                    border: 1px solid;
                                    padding: 10px 10px;
                                    text-align: center;
                                }
                                       \s
                                body {
                                    background-color: #D3D3D3;
                                    position: relative;
                                    text-align: center;
                                    font-family: Arial,serif; size: 11px;
                                }
                               \s

                            </style>
                            <body onload="onload()">
                            <h1>SET08803 2021-2 TR2 001 - Software Engineering Methods</h1>
                            <h2>Coursework - Part 2 - <a href="https://github.com/xx00a/Group6">Group 6</a>
                            </h2>
                            <p>Please select report number that you would like to generate:</p>
                            <div class="selectIDdropdown">
                              <select id="reportID" onchange="change()" onmousedown="erase()">
                                    <option value="0">Select...</option>\s
                		<option value="1">Report 1</option>\s
                                    <option value="2">Report 2</option> <!-- continent -->
                                    <option value="3">Report 3</option> <!-- region -->
                                    <option value="4">Report 4</option>
                                    <option value="5">Report 5</option>
                                    <option value="6">Report 6</option>
                                    <option value="7">Report 7</option>
                                    <option value="8">Report 8</option> <!-- continent -->
                                    <option value="9">Report 9</option> <!-- region -->
                                    <option value="10">Report 10</option> <!-- country -->
                                    <option value="11">Report 11</option> <!-- district -->
                                    <option value="12">Report 12</option>
                                    <option value="13">Report 13</option> <!-- continent -->
                                    <option value="14">Report 14</option> <!-- region -->
                                    <option value="15">Report 15</option> <!-- country -->
                                    <option value="16">Report 16</option> <!-- district -->
                                    <option value="17">Report 17</option>
                                    <option value="18">Report 18</option> <!-- continent -->
                                    <option value="19">Report 19</option> <!-- region -->
                                    <option value="20">Report 20</option>
                                    <option value="21">Report 21</option> <!-- continent -->
                                    <option value="22">Report 22</option> <!-- region -->
                                    <option value="23">Report 23</option>
                                    <option value="24">Report 24</option>
                                    <option value="25">Report 25</option>
                                    <option value="26">Report 26</option>
                                    <option value="27">Report 27</option> <!-- continent -->
                                    <option value="28">Report 28</option> <!-- region -->
                                    <option value="29">Report 29</option> <!-- country -->
                                    <option value="30">Report 30</option> <!-- district -->
                                    <option value="31">Report 31</option> <!-- city -->
                                    <option value="32">Report 32</option>
                                </select>
                            </div>

                <div id="selectionCriteria"></div>
                				
                <select id="groupingTypeDropdown" size="10"></select>
                <br>
                            <input id="limitOf" type="text" placeholder="number of results...">
                            <br>
                            <button id="buttonGenerate" onclick="submit()"> GENERATE REPORT</button>
                              		
                			
                            <script>
                	function onload(){
                		document.getElementById("groupingTypeDropdown").style.display = "none";		
                		document.getElementById("limitOf").style.display = "none";		
                		document.getElementById("buttonGenerate").style.display = "none";		
                	}
                                function submit() {
                                    var a = document.getElementById("reportID");
                                    var reportNumber = (a.options[a.selectedIndex].index);
                                    var groupingType = document.getElementById("groupingTypeDropdown");
                                    var limitType = document.getElementById("limitOf");
                                       \s
                                    window.open("/report?id=" + reportNumber + "&grouping=" + groupingType.value + "&limit=" + limitType.value);
                                    //console.log("/report?id=" + reportNumber + "&grouping=" + groupingType.value + "&limit=" + limitType.value);
                                                                  \s
                                }
                	
                	
                	function erase(){
                		var selectobject = document.getElementById("groupingTypeDropdown");

                		for (var i=0; i < selectobject.length; i++) {
                			selectobject.remove(i);
                			i--;
                			
                		}
                	}
                	
                	
                	function change(){

                		var x = document.getElementById("selectionCriteria");
                                    var a = document.getElementById("reportID");
                                    var reportNumber = (a.options[a.selectedIndex].index);
                		
                		var selectobject = document.getElementById("groupingTypeDropdown");
                		
                		
                							
                		if(reportNumber > 0){
                			document.getElementById("limitOf").style.display = "inline";		
                			document.getElementById("buttonGenerate").style.display = "inline";	
                		}else{
                			document.getElementById("groupingTypeDropdown").style.display = "none";		
                			document.getElementById("limitOf").style.display = "none";		
                			document.getElementById("buttonGenerate").style.display = "none";	
                		}
                								
                		
                		if(reportNumber == 2 || reportNumber == 8 || reportNumber == 13 || reportNumber == 18 || reportNumber == 21 || reportNumber == 27){
                			x.innerHTML = "Please select continent:";
                			document.getElementById("groupingTypeDropdown").style.display = "inline";	
                			selectobject.appendChild(new Option("Africa", "Africa"));
                			selectobject.appendChild(new Option("Antarctica", "Antarctica"));
                			selectobject.appendChild(new Option("Asia", "Asia"));
                			selectobject.appendChild(new Option("Europe", "Europe"));
                			selectobject.appendChild(new Option("North America", "North%20America"));
                			selectobject.appendChild(new Option("Oceania", "Oceania"));
                			selectobject.appendChild(new Option("South America","South%20America"));
                		}
                		else if(reportNumber == 3 || reportNumber == 9 || reportNumber == 14 || reportNumber == 19 || reportNumber == 22 || reportNumber == 28){
                			x.innerHTML = "Please select region:";
                			document.getElementById("groupingTypeDropdown").style.display = "inline";
                			selectobject.appendChild(new Option("Southern and Central Asia","Southern%20and%20Central%20Asia"));
                			selectobject.appendChild(new Option("Central Africa","Central%20Africa"));
                			selectobject.appendChild(new Option("Southern Europe","Southern%20Europe"));
                			selectobject.appendChild(new Option("Middle East","Middle%20East"));
                			selectobject.appendChild(new Option("South America","South%20America"));
                			selectobject.appendChild(new Option("Polynesia","Polynesia"));
                			selectobject.appendChild(new Option("Antarctica","Antarctica"));
                			selectobject.appendChild(new Option("Australia and New Zealand","Australia%20and%20New%20Zealand"));
                			selectobject.appendChild(new Option("Western Europe","Western%20Europe"));
                			selectobject.appendChild(new Option("Eastern Africa","Eastern%20Africa"));
                			selectobject.appendChild(new Option("Western Africa","Western%20Africa"));
                			selectobject.appendChild(new Option("Eastern Europe","Eastern%20Europe"));
                			selectobject.appendChild(new Option("Central America","Central%20America"));
                			selectobject.appendChild(new Option("North America","North%20America"));
                			selectobject.appendChild(new Option("Southeast Asia","Southeast%20Asia"));
                			selectobject.appendChild(new Option("Southern Africa","Southern%20Africa"));
                			selectobject.appendChild(new Option("Eastern Asia","Eastern%20Asia"));
                			selectobject.appendChild(new Option("Nordic Countries","Nordic%20Countries"));
                			selectobject.appendChild(new Option("Northern Africa","Northern%20Africa"));
                			selectobject.appendChild(new Option("Baltic Countries","Baltic%20Countries"));
                			selectobject.appendChild(new Option("Micronesia","Micronesia"));
                			selectobject.appendChild(new Option("British Islands","British%20Islands"));
                			selectobject.appendChild(new Option("Micronesia/Caribbean","Micronesia/Caribbean"));
                			selectobject.appendChild(new Option("Caribbean","Caribbean"));
                		}
                		else if(reportNumber == 10 || reportNumber == 15 || reportNumber == 29){
                			x.innerHTML = "Please select country:";
                		}
                		else if(reportNumber == 11 || reportNumber == 16 || reportNumber == 30){
                			x.innerHTML = "Please select district:";
                		}
                		else if(reportNumber == 31){
                			x.innerHTML = "Please select city:";
                		}else{
                			x.innerHTML = " ";
                			document.getElementById("groupingTypeDropdown").style.display = "none";
                		}
                		
                	}
                	
                            </script>
                            </body>
                            </html>
                    """;

        return htmlOutput;

    }

}