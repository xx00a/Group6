package com.napier.coursework;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AppUnitTest {

    ReportEngine reportEngine = new ReportEngine();

    @Test
    void getReportByIdShouldReturnCorrectReport() {
        Reports report = ReportEngine.getReportById(1);
        assertEquals(Reports.REPORT_ALL_COUNTRIES_BY_POPULATION_DESC, report);
    }

    @Test
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
                "<h2 style=\"size: 14px;\">All the cities in Africa organised by largest population to smallest.</h2>";

        assertEquals(expected, testResult);
        assertTrue(testResult.contains("All the cities in Africa organised by largest population to smallest."));
    }

    @Test
    void generateTableHeaders() {
        String testResult = reportEngine.generateTableHeaders(Reports.REPORT_ALL_CITIES_IN_CONTINENT);
        String expected = "<tr><th>Name</th><th>Country</th><th>District</th><th>Population</th></tr>";
        assertEquals(expected, testResult);
    }

    @Test
    void generateHtmlFooter() {
        String testResult = reportEngine.generateHtmlFooter();
        String expected = "</body></html>";
        assertEquals(expected, testResult);
    }

    @Test
    void produceHomePage() {
        App app;
        app = new App();
        String testResult =  app.produceHomePage();
        assertNotNull(testResult);
        assertTrue(testResult.contains("Bolivia"));
        assertTrue(testResult.contains("Barbados"));
        assertTrue(testResult.contains("<p style=\"font-family:Garamond;\">Please select report number that you would like to generate:</p>"));
    }

}
