/*
  SET08803 Coursework Application
  ReportEngine Class
  This class builds the requested report based on the classes assigned.
*/

// Dependencies
package com.napier.coursework;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.napier.coursework.QueryHelper.getResultSet;

// ReportEngine
public class ReportEngine {

    // start ResultSet
    protected ResultSet getDataFromDatabase(Connection connection, Reports report, String grouping, String limit) throws SQLException {

        // Retrieve the requested SQL command
        String queryString = report.getQuery();

        // Search and replace values in SQL query
        queryString = queryString.replaceAll("XXvarArgXX", grouping);
        queryString = queryString.replaceAll("YYvarLimitYY", limit);

        // Return the result
        return getResultSet(connection, queryString);
    }
    // end ResultSet

    // start getReportById
    public static Reports getReportById(int reportId) {

        // find report ID
        for (Reports report : Reports.values()) {
            if (report.id == reportId) {
                return report;
            }
        }

        // return default value
        return Reports.REPORT_ALL_COUNTRIES_BY_POPULATION_DESC;
    }
    // end getReportById

    // Return HTML output method
    // start generateHtmlOutput
    protected String generateHtmlOutput(ResultSet dataFromDb, Reports report, String grouping, String limit) throws SQLException {

        // create our header HTML first
        String htmlOutput = generateHtmlHeader(report, grouping, limit);

        // add table structure and call report methods
        htmlOutput += "<table>";
        htmlOutput += generateTableHeaders(report);
        htmlOutput += generateTableRows(dataFromDb, report);
        htmlOutput += "</table>";
        htmlOutput += generateHtmlFooter();
        return htmlOutput;
    }
    // end generateHtmlOutput

    // Create the HTML page structure and header
    // start generateHtmlHeader
    protected String generateHtmlHeader(Reports report, String grouping, String limit) {

        // call on report details
        String description = report.getReportName();
        String headerOfReport = report.getReportHeader();
        String reportId = report.getId().toString();

        // search and replace keywords as needed
        description = description.replaceAll("XXvarArgXX", grouping);
        description = description.replaceAll("YYvarLimitYY", limit);

        // header string
        String pageHeader =
                """
                         <!DOCTYPE html>
                         <html lang="en">
                         <head>
                             <meta charset="UTF-8">
                             <title>Report</title>
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
                         </style>
                         <body style="font-family: Arial,serif; size: 11px; background-color: #D3D3D3;">
                        """;

        String reportHeader = "<h2 style=\"size: 14px;\">" + headerOfReport + "</h2>";
        // report description
        String htmlDesc = "<h3 style=\"color: #666666;\">(Report ID: " + reportId + ") " + description + "</h3>";
        pageHeader += reportHeader;
        pageHeader += htmlDesc;

        // return header
        return pageHeader;
    }
    // end generateHtmlHeader

    // Create table from matrix
    // start generateTableHeaders
    protected String generateTableHeaders(Reports report) {
        StringBuilder htmlHeaders = new StringBuilder();
        ReportTypes type = report.getReportType();
        List<String> headers = type.getHeaders();
        htmlHeaders.append("<tr>");
        for (String header : headers) {
            htmlHeaders.append("<th>").append(header).append("</th>");
        }
        htmlHeaders.append("</tr>");
        return htmlHeaders.toString();
    }
    // end generateTableHeaders

    // Create table rows from matrix
    // start generateTableRows
    protected String generateTableRows(ResultSet dataFromDb, Reports report) throws SQLException {
        StringBuilder htmlRows = new StringBuilder();
        while (dataFromDb.next()) {
            htmlRows.append("<tr>");
            for (int i = 1; i <= report.getReportType().getHeaders().size(); i++) {
                htmlRows.append("<td>").append(dataFromDb.getString(i)).append("</td>");
            }
            htmlRows.append("</tr>");
        }
        return htmlRows.toString();
    }
    // end generateTableRows

    // Quick method to create HTML footer
    // start generateHtmlFooter
    protected String generateHtmlFooter() {
        return "</body></html>";
    }
    // end generateHtmlFooter

}
