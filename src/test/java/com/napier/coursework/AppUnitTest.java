/*
  SET08803 Coursework Application
  AppIntegrationTest Class
  This class is a basis for application integration testing
*/

// Dependencies
package com.napier.coursework;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// start class AppUnitTest
public class AppUnitTest {

    ReportEngine reportEngine = new ReportEngine();

    @Test
        // test getReportByIdShouldReturnCorrectReport
    void getReportByIdShouldReturnCorrectReport() {
        Reports report = ReportEngine.getReportById(1);
        assertEquals(Reports.REPORT_ALL_COUNTRIES_BY_POPULATION_DESC, report);
    }
    // end test

    @Test
        // test generateHtmlHeaderShouldReturnCorrectHeader
    void generateHtmlHeaderShouldReturnCorrectHeader() {
        String testResult = reportEngine.generateHtmlHeader(Reports.REPORT_ALL_CITIES_IN_CONTINENT, "Africa", "10");
        String expected = " <!DOCTYPE html>\n" +
                " <html lang=\"en\">\n" +
                " <head>\n" +
                "     <meta charset=\"UTF-8\">\n" +
                "     <title>Report</title>\n" +
                " </head>\n" +
                " <style>\n" +
                "     table {\n" +
                "         border-collapse: collapse;\n" +
                "         border: 1px solid;\n" +
                "         font-family: sans-serif;\n" +
                "     }\n" +
                "\n" +
                "     th, td {\n" +
                "         border: 1px solid;\n" +
                "         padding: 10px 10px;\n" +
                "         text-align: center;\n" +
                "     }\n" +
                " </style>\n" +
                " <body style=\"font-family: Arial,serif; size: 11px; background-color: #D3D3D3;\">\n" +
                "<h2 style=\"size: 14px;\">City Report</h2><h3 style=\"color: #666666;\">(Report ID: 8) All the cities in Africa organised by largest population to smallest.</h3>";

        assertEquals(expected, testResult);
        assertTrue(testResult.contains("All the cities in Africa organised by largest population to smallest."));
    }
    // end test

    @Test
        // test generateTableHeadersShouldReturnCorrectTableHeaders
    void generateTableHeadersShouldReturnCorrectTableHeaders() {
        String testResult = reportEngine.generateTableHeaders(Reports.REPORT_ALL_CITIES_IN_CONTINENT);
        String expected = "<tr><th>Name</th><th>Country</th><th>District</th><th>Population</th></tr>";
        assertEquals(expected, testResult);
    }
    // end test

    @Test
        // test generateHtmlFooterShouldReturnCorrectFooter
    void generateHtmlFooterShouldReturnCorrectFooter() {
        String testResult = reportEngine.generateHtmlFooter();
        String expected = "</body></html>";
        assertEquals(expected, testResult);
    }
    // end test

    @Test
        // test produceHomePageShouldReturnCorrectHomePage
    void produceHomePageShouldReturnCorrectHomePage() {
        App app;
        app = new App();
        String testResult = app.produceHomePage();
        assertNotNull(testResult);
        assertTrue(testResult.contains("Bolivia"));
        assertTrue(testResult.contains("Barbados"));
        assertTrue(testResult.contains("<p>Please select report number that you would like to generate:</p>"));
    }
// end test

    @Test
        // test getResultSetShouldThrowExceptionOnError
    void getResultSetShouldThrowExceptionOnError() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            QueryHelper.getResultSet(null, null);
        });
        assertNotNull(exception);
        assertEquals("Cannot invoke \"java.sql.Connection.createStatement()\" because \"connection\" is null", exception.getMessage());
    }
// end test

    @Test
        // test getIdShouldReturnCorrectId
    void getIdShouldReturnCorrectId() {
        Integer testResult = Reports.REPORT_ALL_COUNTRIES_BY_POPULATION_DESC.getId();
        assertNotNull(testResult);
        assertEquals(1, testResult);
    }
    // end test

    @Test
        // test getReportHeaderShouldReturnCorrectHeader
    void getReportHeaderShouldReturnCorrectHeader() {
        String testResult = Reports.REPORT_ALL_COUNTRIES_BY_POPULATION_DESC.getReportHeader();
        assertNotNull(testResult);
        assertEquals("Country Report", testResult);
    }
    // end test

    @Test
        // getReportByIdShouldReturnDefaultValue
    void getReportByIdShouldReturnDefaultValue() {
        Reports reportById = ReportEngine.getReportById(10000);
        assertEquals(Reports.REPORT_ALL_COUNTRIES_BY_POPULATION_DESC, reportById);
        assertNotEquals(Reports.REPORT_ALL_CITIES_IN_CONTINENT, reportById);
    }
    // end test
}
// end class