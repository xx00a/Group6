/*
  SET08803 Coursework Application
  Main Class
*/

// Dependencies
package com.napier.coursework;
import java.sql.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

// Set this class as the Spring Boot application and as the REST API controller
@SpringBootApplication
@RestController
public class App {


    public static Connection sqlConnect;

    // start main
    public static void main(String[] args) {

        // Initialise the connection to the "world" database on the MySQL db server
        sqlConnect = MySQLConnection.connect("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");

        // Start our Spring Boot application
        SpringApplication.run(App.class, args);

        // Add a line in the console log to wait for a request to come through
        System.out.println("Group6's website is now up and running. Waiting for http request...");

    }
    // end main

    // Coursework API where report generation request will go through and will be returned to the NGINX webserver
    @RequestMapping(value = "/report",
            params = {"id", "grouping", "limit"},
            method = RequestMethod.GET)

    // getReport will collect the various parameters from the webpage to produce the report
    // start getReport
    @ResponseBody
    public String getReport(@RequestParam(value = "id", defaultValue = "1") int ID, @RequestParam(value = "grouping", defaultValue = "") String grouping,
                            @RequestParam(value = "limit", defaultValue = "1") String limit) throws SQLException {

        // Create  the html report output
        ReportEngine reportEngine = new ReportEngine();

        // Lookup our report ID
        Reports report = ReportEngine.getReportById(ID);

        // Let's return the dataset from the SQL database
        ResultSet dataFromDb = reportEngine.getDataFromDatabase(sqlConnect, report, grouping, limit);

        // we are returning the HTML (strings) to display to the HTTP engine
        return reportEngine.generateHtmlOutput(dataFromDb, report, grouping, limit);
    }
    // end getReport

    // The index page is where we will collect the parameters (via GET)
    // http://localhost:8080/index
    // start produceHomePage
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String produceHomePage() {

        // Create variable for the html output
        String htmlOutput;

        // Assign the static HTML page
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
                                    <option value="3">Report 3</option> <!-- region -->\s
                                    <option value="4">Report 4</option> <!-- NEEDS A USER INPUT FOR NUMBER -->
                                    <option value="5">Report 5</option>  <!-- continent --> <!-- NEEDS A USER INPUT FOR NUMBER -->
                                    <option value="6">Report 6</option>  <!-- region --> <!-- NEEDS A USER INPUT FOR NUMBER -->
                                    <option value="7">Report 7</option>
                                    <option value="8">Report 8</option> <!-- continent -->
                                    <option value="9">Report 9</option> <!-- region -->
                                    <option value="10">Report 10</option> <!-- country -->
                                    <option value="11">Report 11</option> <!-- district -->
                                    <option value="12">Report 12</option> <!-- NEEDS A USER INPUT FOR NUMBER -->
                                    <option value="13">Report 13</option> <!-- continent --> <!-- NEEDS A USER INPUT FOR NUMBER -->
                                    <option value="14">Report 14</option> <!-- region --> <!-- NEEDS A USER INPUT FOR NUMBER -->
                                    <option value="15">Report 15</option> <!-- country --> <!-- NEEDS A USER INPUT FOR NUMBER -->
                                    <option value="16">Report 16</option> <!-- district --> <!-- NEEDS A USER INPUT FOR NUMBER -->
                                    <option value="17">Report 17</option>
                                    <option value="18">Report 18</option> <!-- continent -->
                                    <option value="19">Report 19</option> <!-- region -->
                                    <option value="20">Report 20</option> <!-- NEEDS A USER INPUT FOR NUMBER -->
                                    <option value="21">Report 21</option> <!-- continent --> <!-- NEEDS A USER INPUT FOR NUMBER -->
                                    <option value="22">Report 22</option> <!-- region --> <!-- NEEDS A USER INPUT FOR NUMBER -->
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
                		
                		if(reportNumber == 4 || reportNumber == 5 || reportNumber == 6 || reportNumber == 12 || reportNumber == 13 || reportNumber == 14 || reportNumber == 15 || reportNumber == 16 || reportNumber == 20 || reportNumber == 21 || reportNumber == 22){
                			document.getElementById("limitOf").style.display = "inline";						
                		}else{
                			document.getElementById("limitOf").style.display = "none";
                		}
                		
                		if(reportNumber == 2 || reportNumber == 5 || reportNumber == 8 || reportNumber == 13 || reportNumber == 18 || reportNumber == 21 || reportNumber == 27){
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
                		else if(reportNumber == 3 || reportNumber == 6 || reportNumber == 9 || reportNumber == 14 || reportNumber == 19 || reportNumber == 22 || reportNumber == 28){
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
                			document.getElementById("groupingTypeDropdown").style.display = "inline";
                			selectobject.appendChild(new Option("Aruba","Aruba"));			
                			selectobject.appendChild(new Option("Afghanistan","Afghanistan"));
                			selectobject.appendChild(new Option("Angola","Angola"));
                			selectobject.appendChild(new Option("Anguilla","Anguilla"));
                			selectobject.appendChild(new Option("Albania","Albania"));
                			selectobject.appendChild(new Option("Andorra","Andorra"));
                			selectobject.appendChild(new Option("Netherlands Antilles","Netherlands%20Antilles"));
                			selectobject.appendChild(new Option("United Arab Emirates","United%20Arab%20Emirates"));
                			selectobject.appendChild(new Option("Argentina","Argentina"));
                			selectobject.appendChild(new Option("Armenia","Armenia"));
                			selectobject.appendChild(new Option("American Samoa","American%20Samoa"));
                			selectobject.appendChild(new Option("Antarctica","Antarctica"));
                			selectobject.appendChild(new Option("French Southern territories","French%20Southern%20territories"));
                			selectobject.appendChild(new Option("Antigua and Barbuda","Antigua%20and%20Barbuda"));
                			selectobject.appendChild(new Option("Australia","Australia"));
                			selectobject.appendChild(new Option("Austria","Austria"));
                			selectobject.appendChild(new Option("Azerbaijan","Azerbaijan"));
                			selectobject.appendChild(new Option("Burundi","Burundi"));
                			selectobject.appendChild(new Option("Belgium","Belgium"));
                			selectobject.appendChild(new Option("Benin","Benin"));
                			selectobject.appendChild(new Option("Burkina Faso","Burkina%20Faso"));
                			selectobject.appendChild(new Option("Bangladesh","Bangladesh"));
                			selectobject.appendChild(new Option("Bulgaria","Bulgaria"));
                			selectobject.appendChild(new Option("Bahrain","Bahrain"));
                			selectobject.appendChild(new Option("Bahamas","Bahamas"));
                			selectobject.appendChild(new Option("Bosnia and Herzegovina","Bosnia%20and%20Herzegovina"));
                			selectobject.appendChild(new Option("Belarus","Belarus"));
                			selectobject.appendChild(new Option("Belize","Belize"));
                			selectobject.appendChild(new Option("Bermuda","Bermuda"));
                			selectobject.appendChild(new Option("Bolivia","Bolivia"));
                			selectobject.appendChild(new Option("Brazil","Brazil"));
                			selectobject.appendChild(new Option("Barbados","Barbados"));
                			selectobject.appendChild(new Option("Brunei","Brunei"));
                			selectobject.appendChild(new Option("Bhutan","Bhutan"));
                			selectobject.appendChild(new Option("Bouvet Island","Bouvet%20Island"));
                			selectobject.appendChild(new Option("Botswana","Botswana"));
                			selectobject.appendChild(new Option("Central African Republic","Central%20African%20Republic"));
                			selectobject.appendChild(new Option("Canada","Canada"));
                			selectobject.appendChild(new Option("Cocos (Keeling) Islands","Cocos%20(Keeling)%20Islands"));
                			selectobject.appendChild(new Option("Switzerland","Switzerland"));
                			selectobject.appendChild(new Option("Chile","Chile"));
                			selectobject.appendChild(new Option("China","China"));
                			selectobject.appendChild(new Option("Cte dIvoire","Cte%20dIvoire"));
                			selectobject.appendChild(new Option("Cameroon","Cameroon"));
                			selectobject.appendChild(new Option("Congo, The Democratic Republic of the","Congo,%20The%20Democratic%20Republic%20of%20the"));
                			selectobject.appendChild(new Option("Congo","Congo"));
                			selectobject.appendChild(new Option("Cook Islands","Cook%20Islands"));
                			selectobject.appendChild(new Option("Colombia","Colombia"));
                			selectobject.appendChild(new Option("Comoros","Comoros"));
                			selectobject.appendChild(new Option("Cape Verde","Cape%20Verde"));
                			selectobject.appendChild(new Option("Costa Rica","Costa%20Rica"));
                			selectobject.appendChild(new Option("Cuba","Cuba"));
                			selectobject.appendChild(new Option("Christmas Island","Christmas%20Island"));
                			selectobject.appendChild(new Option("Cayman Islands","Cayman%20Islands"));
                			selectobject.appendChild(new Option("Cyprus","Cyprus"));
                			selectobject.appendChild(new Option("Czech Republic","Czech%20Republic"));
                			selectobject.appendChild(new Option("Germany","Germany"));
                			selectobject.appendChild(new Option("Djibouti","Djibouti"));
                			selectobject.appendChild(new Option("Dominica","Dominica"));
                			selectobject.appendChild(new Option("Denmark","Denmark"));
                			selectobject.appendChild(new Option("Dominican Republic","Dominican%20Republic"));
                			selectobject.appendChild(new Option("Algeria","Algeria"));
                			selectobject.appendChild(new Option("Ecuador","Ecuador"));
                			selectobject.appendChild(new Option("Egypt","Egypt"));
                			selectobject.appendChild(new Option("Eritrea","Eritrea"));
                			selectobject.appendChild(new Option("Western Sahara","Western%20Sahara"));
                			selectobject.appendChild(new Option("Spain","Spain"));
                			selectobject.appendChild(new Option("Estonia","Estonia"));
                			selectobject.appendChild(new Option("Ethiopia","Ethiopia"));
                			selectobject.appendChild(new Option("Finland","Finland"));
                			selectobject.appendChild(new Option("Fiji Islands","Fiji%20Islands"));
                			selectobject.appendChild(new Option("Falkland Islands","Falkland%20Islands"));
                			selectobject.appendChild(new Option("France","France"));
                			selectobject.appendChild(new Option("Faroe Islands","Faroe%20Islands"));
                			selectobject.appendChild(new Option("Micronesia, Federated States of","Micronesia,%20Federated%20States%20of"));
                			selectobject.appendChild(new Option("Gabon","Gabon"));
                			selectobject.appendChild(new Option("United Kingdom","United%20Kingdom"));
                			selectobject.appendChild(new Option("Georgia","Georgia"));
                			selectobject.appendChild(new Option("Ghana","Ghana"));
                			selectobject.appendChild(new Option("Gibraltar","Gibraltar"));
                			selectobject.appendChild(new Option("Guinea","Guinea"));
                			selectobject.appendChild(new Option("Guadeloupe","Guadeloupe"));
                			selectobject.appendChild(new Option("Gambia","Gambia"));
                			selectobject.appendChild(new Option("Guinea-Bissau","Guinea-Bissau"));
                			selectobject.appendChild(new Option("Equatorial Guinea","Equatorial%20Guinea"));
                			selectobject.appendChild(new Option("Greece","Greece"));
                			selectobject.appendChild(new Option("Grenada","Grenada"));
                			selectobject.appendChild(new Option("Greenland","Greenland"));
                			selectobject.appendChild(new Option("Guatemala","Guatemala"));
                			selectobject.appendChild(new Option("French Guiana","French%20Guiana"));
                			selectobject.appendChild(new Option("Guam","Guam"));
                			selectobject.appendChild(new Option("Guyana","Guyana"));
                			selectobject.appendChild(new Option("Hong Kong","Hong Kong"));
                			selectobject.appendChild(new Option("Heard Island and McDonald Islands","Heard%20Island%20and%20McDonald%20Islands"));
                			selectobject.appendChild(new Option("Honduras","Honduras"));
                			selectobject.appendChild(new Option("Croatia","Croatia"));
                			selectobject.appendChild(new Option("Haiti","Haiti"));
                			selectobject.appendChild(new Option("Hungary","Hungary"));
                			selectobject.appendChild(new Option("Indonesia","Indonesia"));
                			selectobject.appendChild(new Option("India","India"));
                			selectobject.appendChild(new Option("British Indian Ocean Territory","British%20Indian%20Ocean%20Territory"));
                			selectobject.appendChild(new Option("Ireland","Ireland"));
                			selectobject.appendChild(new Option("Iran","Iran"));
                			selectobject.appendChild(new Option("Iraq","Iraq"));
                			selectobject.appendChild(new Option("Iceland","Iceland"));
                			selectobject.appendChild(new Option("Israel","Israel"));
                			selectobject.appendChild(new Option("Italy","Italy"));
                			selectobject.appendChild(new Option("Jamaica","Jamaica"));
                			selectobject.appendChild(new Option("Jordan","Jordan"));
                			selectobject.appendChild(new Option("Japan","Japan"));
                			selectobject.appendChild(new Option("Kazakstan","Kazakstan"));
                			selectobject.appendChild(new Option("Kenya","Kenya"));
                			selectobject.appendChild(new Option("Kyrgyzstan","Kyrgyzstan"));
                			selectobject.appendChild(new Option("Cambodia","Cambodia"));
                			selectobject.appendChild(new Option("Kiribati","Kiribati"));
                			selectobject.appendChild(new Option("Saint Kitts and Nevis","Saint%20Kitts%20and%20Nevis"));
                			selectobject.appendChild(new Option("South Korea","South%20Korea"));
                			selectobject.appendChild(new Option("Kuwait","Kuwait"));
                			selectobject.appendChild(new Option("Laos","Laos"));
                			selectobject.appendChild(new Option("Lebanon","Lebanon"));
                			selectobject.appendChild(new Option("Liberia","Liberia"));
                			selectobject.appendChild(new Option("Libyan Arab Jamahiriya","Libyan%20Arab%20Jamahiriya"));
                			selectobject.appendChild(new Option("Saint Lucia","Saint%20Lucia"));
                			selectobject.appendChild(new Option("Liechtenstein","Liechtenstein"));
                			selectobject.appendChild(new Option("Sri Lanka","Sri%20Lanka"));
                			selectobject.appendChild(new Option("Lesotho","Lesotho"));
                			selectobject.appendChild(new Option("Lithuania","Lithuania"));
                			selectobject.appendChild(new Option("Luxembourg","Luxembourg"));
                			selectobject.appendChild(new Option("Latvia","Latvia"));
                			selectobject.appendChild(new Option("Macao","Macao"));
                			selectobject.appendChild(new Option("Morocco","Morocco"));
                			selectobject.appendChild(new Option("Monaco","Monaco"));
                			selectobject.appendChild(new Option("Moldova","Moldova"));
                			selectobject.appendChild(new Option("Madagascar","Madagascar"));
                			selectobject.appendChild(new Option("Maldives","Maldives"));
                			selectobject.appendChild(new Option("Mexico","Mexico"));
                			selectobject.appendChild(new Option("Marshall Islands","Marshall%20Islands"));
                			selectobject.appendChild(new Option("Macedonia","Macedonia"));
                			selectobject.appendChild(new Option("Mali","Mali"));
                			selectobject.appendChild(new Option("Malta","Malta"));
                			selectobject.appendChild(new Option("Myanmar","Myanmar"));
                			selectobject.appendChild(new Option("Mongolia","Mongolia"));
                			selectobject.appendChild(new Option("Northern Mariana Islands","Northern%20Mariana%20Islands"));
                			selectobject.appendChild(new Option("Mozambique","Mozambique"));
                			selectobject.appendChild(new Option("Mauritania","Mauritania"));
                			selectobject.appendChild(new Option("Montserrat","Montserrat"));
                			selectobject.appendChild(new Option("Martinique","Martinique"));
                			selectobject.appendChild(new Option("Mauritius","Mauritius"));
                			selectobject.appendChild(new Option("Malawi","Malawi"));
                			selectobject.appendChild(new Option("Malaysia","Malaysia"));
                			selectobject.appendChild(new Option("Mayotte","Mayotte"));
                			selectobject.appendChild(new Option("Namibia","Namibia"));
                			selectobject.appendChild(new Option("New Caledonia","New%20Caledonia"));
                			selectobject.appendChild(new Option("Niger","Niger"));
                			selectobject.appendChild(new Option("Norfolk Island","Norfolk%20Island"));
                			selectobject.appendChild(new Option("Nigeria","Nigeria"));
                			selectobject.appendChild(new Option("Nicaragua","Nicaragua"));
                			selectobject.appendChild(new Option("Niue","Niue"));
                			selectobject.appendChild(new Option("Netherlands","Netherlands"));
                			selectobject.appendChild(new Option("Norway","Norway"));
                			selectobject.appendChild(new Option("Nepal","Nepal"));
                			selectobject.appendChild(new Option("Nauru","Nauru"));
                			selectobject.appendChild(new Option("New Zealand","New%20Zealand"));
                			selectobject.appendChild(new Option("Oman","Oman"));
                			selectobject.appendChild(new Option("Pakistan","Pakistan"));
                			selectobject.appendChild(new Option("Panama","Panama"));
                			selectobject.appendChild(new Option("Pitcairn","Pitcairn"));
                			selectobject.appendChild(new Option("Peru","Peru"));
                			selectobject.appendChild(new Option("Philippines","Philippines"));
                			selectobject.appendChild(new Option("Palau","Palau"));
                			selectobject.appendChild(new Option("Papua New Guinea","Papua%20New%20Guinea"));
                			selectobject.appendChild(new Option("Poland","Poland"));
                			selectobject.appendChild(new Option("Puerto Rico","Puerto%20Rico"));
                			selectobject.appendChild(new Option("North Korea","North%20Korea"));
                			selectobject.appendChild(new Option("Portugal","Portugal"));
                			selectobject.appendChild(new Option("Paraguay","Paraguay"));
                			selectobject.appendChild(new Option("Palestine","Palestine"));
                			selectobject.appendChild(new Option("French Polynesia","French%20Polynesia"));
                			selectobject.appendChild(new Option("Qatar","Qatar"));
                			selectobject.appendChild(new Option("Runion","Runion"));
                			selectobject.appendChild(new Option("Romania","Romania"));
                			selectobject.appendChild(new Option("Russian Federation","Russian%20Federation"));
                			selectobject.appendChild(new Option("Rwanda","Rwanda"));
                			selectobject.appendChild(new Option("Saudi Arabia","Saudi%20Arabia"));
                			selectobject.appendChild(new Option("Sudan","Sudan"));
                			selectobject.appendChild(new Option("Senegal","Senegal"));
                			selectobject.appendChild(new Option("Singapore","Singapore"));
                			selectobject.appendChild(new Option("South Georgia and the South Sandwich Islands","South%20Georgia%20and%20the%20South%20Sandwich%20Islands"));
                			selectobject.appendChild(new Option("Saint Helena","Saint%20Helena"));
                			selectobject.appendChild(new Option("Svalbard and Jan Mayen","Svalbard%20and%20Jan%20Mayen"));
                			selectobject.appendChild(new Option("Solomon Islands","Solomon%20Islands"));
                			selectobject.appendChild(new Option("Sierra Leone","Sierra%20Leone"));
                			selectobject.appendChild(new Option("El Salvador","El%20Salvador"));
                			selectobject.appendChild(new Option("San Marino","San%20Marino"));
                			selectobject.appendChild(new Option("Somalia","Somalia"));
                			selectobject.appendChild(new Option("Saint Pierre and Miquelon","Saint%20Pierre%20and%20Miquelon"));
                			selectobject.appendChild(new Option("Sao Tome and Principe","Sao%20Tome%20and%20Principe"));
                			selectobject.appendChild(new Option("Suriname","Suriname"));
                			selectobject.appendChild(new Option("Slovakia","Slovakia"));
                			selectobject.appendChild(new Option("Slovenia","Slovenia"));
                			selectobject.appendChild(new Option("Sweden","Sweden"));
                			selectobject.appendChild(new Option("Swaziland","Swaziland"));
                			selectobject.appendChild(new Option("Seychelles","Seychelles"));
                			selectobject.appendChild(new Option("Syria","Syria"));
                			selectobject.appendChild(new Option("Turks and Caicos Islands","Turks%20and%20Caicos%20Islands"));
                			selectobject.appendChild(new Option("Chad","Chad"));
                			selectobject.appendChild(new Option("Togo","Togo"));
                			selectobject.appendChild(new Option("Thailand","Thailand"));
                			selectobject.appendChild(new Option("Tajikistan","Tajikistan"));
                			selectobject.appendChild(new Option("Tokelau","Tokelau"));
                			selectobject.appendChild(new Option("Turkmenistan","Turkmenistan"));
                			selectobject.appendChild(new Option("East Timor","East%20Timor"));
                			selectobject.appendChild(new Option("Tonga","Tonga"));
                			selectobject.appendChild(new Option("Trinidad and Tobago","Trinidad%20and%20Tobago"));
                			selectobject.appendChild(new Option("Tunisia","Tunisia"));
                			selectobject.appendChild(new Option("Turkey","Turkey"));
                			selectobject.appendChild(new Option("Tuvalu","Tuvalu"));
                			selectobject.appendChild(new Option("Tuvalu","Tuvalu"));
                			selectobject.appendChild(new Option("Tanzania","Tanzania"));
                			selectobject.appendChild(new Option("Uganda","Uganda"));
                			selectobject.appendChild(new Option("Ukraine","Ukraine"));
                			selectobject.appendChild(new Option("United States Minor Outlying Islands","United%20States%20Minor%20Outlying%20Islands"));
                			selectobject.appendChild(new Option("Uruguay","Uruguay"));
                			selectobject.appendChild(new Option("United States","United%20States"));
                			selectobject.appendChild(new Option("Uzbekistan","Uzbekistan"));
                			selectobject.appendChild(new Option("Holy See (Vatican City State)","Holy%20See%20(Vatican%20City%20State)"));
                			selectobject.appendChild(new Option("Saint Vincent and the Grenadines","Saint%20Vincent%20and%20the%20Grenadines"));
                			selectobject.appendChild(new Option("Venezuela","Venezuela"));
                			selectobject.appendChild(new Option("Virgin Islands, British","Virgin%20Islands,%20British"));
                			selectobject.appendChild(new Option("Virgin Islands, U.S.","Virgin%20Islands, U.S."));
                			selectobject.appendChild(new Option("Vietnam","Vietnam"));
                			selectobject.appendChild(new Option("Vanuatu","Vanuatu"));
                			selectobject.appendChild(new Option("Wallis and Futuna","Wallis%20and%20Futuna"));
                			selectobject.appendChild(new Option("Samoa","Samoa"));
                			selectobject.appendChild(new Option("Yemen","Yemen"));
                			selectobject.appendChild(new Option("Yugoslavia","Yugoslavia"));
                			selectobject.appendChild(new Option("South Africa","South%20Africa"));
                			selectobject.appendChild(new Option("Zambia","Zambia"));
                			selectobject.appendChild(new Option("Zimbabwe","Zimbabwe"));
                		}
                		else if(reportNumber == 11 || reportNumber == 16 || reportNumber == 30){
                			x.innerHTML = "Please select district:";
                			document.getElementById("groupingTypeDropdown").style.display = "inline";	
                			selectobject.appendChild(new Option("Upolu", "Upolu"));
                			selectobject.appendChild(new Option("Tahiti", "Tahiti"));
                			selectobject.appendChild(new Option("Fakaofo", "Fakaofo"));
                			selectobject.appendChild(new Option("Seoul", "Seoul"));
                			selectobject.appendChild(new Option("Wallis", "Wallis"));
                		}
                		else if(reportNumber == 31){
                			x.innerHTML = "Please select city:";
                			document.getElementById("groupingTypeDropdown").style.display = "inline";	
                			selectobject.appendChild(new Option("New York", "New%20York"));
                			selectobject.appendChild(new Option("London", "London"));
                			selectobject.appendChild(new Option("Peking", "Jakarta"));
                			selectobject.appendChild(new Option("Hanoi", "Hanoi"));
                			selectobject.appendChild(new Option("Tokyo", "Tokyo"));
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
    // end produceHomePage

}