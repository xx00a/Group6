/*
  SET08803 Coursework Application
  QueryHelper Class
  This class is a wrapper to digest SQL commands to the SQL connection
*/

// Dependencies
package com.napier.coursework;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryHelper {

    private QueryHelper() {
        // empty method
    }

    // start getResultSet
    public static ResultSet getResultSet(Connection connection, String query) throws SQLException {
        // Create an SQL statement
        Statement stmt = connection.createStatement();
        // Execute SQL statement
        return stmt.executeQuery(query);
    }
    // end getResultSet
}
