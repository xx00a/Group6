package com.napier.coursework;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryHelper {

    private QueryHelper() {}

    public static ResultSet getResultSet(Connection connection, String query) throws SQLException {
        // Create an SQL statement
        Statement stmt = connection.createStatement();
        // Execute SQL statement
        return stmt.executeQuery(query);
    }
}
