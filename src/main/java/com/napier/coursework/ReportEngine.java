package com.napier.coursework;

/*
 * The report engine outputs a selected report ID for display
 * Last update: April 22, 2022
 */


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.napier.coursework.QueryHelper.getResultSet;

public class ReportEngine {

    protected ResultSet getDataFromDatabase(Connection connection, Reports report, String grouping, String limit) throws SQLException {
        String queryString = report.getQuery();
        queryString = queryString.replaceAll("XXvarArgXX", grouping);
        queryString = queryString.replaceAll("YYvarLimitYY", limit);
        return getResultSet(connection, queryString);
    }

    public static Reports getReportById(int reportId) {
        for (Reports report : Reports.values()) {
            if (report.id == reportId) {
                return report;
            }
        }

        return Reports.REPORT_ALL_COUNTRIES_BY_POPULATION_DESC;
    }

    protected String generateHtmlOutput(ResultSet dataFromDb, Reports report, String grouping, String limit) throws SQLException {

        String htmlOutput = generateHtmlHeader(report, grouping, limit);
        htmlOutput += "<table>";
        htmlOutput += generateTableHeaders(report);
        htmlOutput += generateTableRows(dataFromDb, report);
        htmlOutput += "</table>";
        htmlOutput += generateHtmlFooter();
        return htmlOutput;
    }

    protected String generateHtmlHeader(Reports report, String grouping, String limit) {
        String description = report.getReportName();
        description = description.replaceAll("XXvarArgXX", grouping);
        description = description.replaceAll("YYvarLimitYY", limit);
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
        String name = "<h2 style=\"size: 14px;\">" + description + "</h2>";
        pageHeader += name;

        return pageHeader;
    }

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

    protected String generateHtmlFooter() {
        return "</body></html>";
    }

}
