package com.napier.coursework;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.*;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AppIntegrationTest {

    static App app;
    ReportEngine reportEngine = new ReportEngine();

    @BeforeAll
    static void init() {
        app = new App();
        App.sqlConnect = MySQLConnection.connect("jdbc:mysql://localhost:33060/world?allowPublicKeyRetrieval=true&useSSL=false", "root", "example");

    }

    @Test
    void generateHtmlOutputShouldReturnCorrectHtmlOutput() throws SQLException {
        ResultSet dataFromDb = reportEngine.getDataFromDatabase(App.sqlConnect, Reports.REPORT_ALL_CITIES_IN_COUNTRY, "Poland", "3");
        String testResult = reportEngine.generateHtmlOutput(dataFromDb, Reports.REPORT_ALL_CITIES_IN_COUNTRY, "Poland", "3");
        assertNotNull(testResult);
        assertTrue(testResult.contains("All the cities in Poland organised by largest population to smallest"));
        assertTrue(testResult.contains("Warszawa"));
    }

    @Test
    void generateTableRowsShouldReturnCorrectTableRows() throws SQLException {
        ResultSet dataFromDb = reportEngine.getDataFromDatabase(App.sqlConnect, Reports.REPORT_ALL_CITIES_IN_COUNTRY, "Poland", "3");
        String testResult = reportEngine.generateTableRows(dataFromDb, Reports.REPORT_ALL_CITIES_IN_COUNTRY);
        assertNotNull(testResult);
        assertTrue(testResult.contains("Warszawa"));
        assertTrue(testResult.contains("Poland"));
        assertTrue(testResult.contains("Kraków"));
        String expected = "<tr><td>Warszawa</td><td>Poland</td><td>Mazowieckie</td><td>1615369</td>" +
                "</tr><tr><td>Lódz</td><td>Poland</td><td>Lodzkie</td><td>800110</td></tr>" +
                "<tr><td>Kraków</td><td>Poland</td><td>Malopolskie</td><td>738150</td></tr>";
        assertEquals(expected, testResult);
    }

    @Test
    void getDataFromDatabaseShouldReturnResultSet() throws SQLException {
        ResultSet testResult = reportEngine.getDataFromDatabase(App.sqlConnect, Reports.REPORT_ALL_CITIES_IN_COUNTRY, "Poland", "3");
        assertNotNull(testResult);
    }

    @Test
    void getReportShouldReturnCorrectReportHtmlOutput() throws SQLException {
        String testResult = app.getReport(8, "Africa", "10");
        assertNotNull(testResult);
        assertTrue(testResult.contains("All the cities in Africa organised by largest population to smallest."));
        assertTrue(testResult.contains("Kairo"));
    }

    @AfterAll
    static void disconnectDB() {
        MySQLConnection.disconnect(App.sqlConnect);
    }
}
