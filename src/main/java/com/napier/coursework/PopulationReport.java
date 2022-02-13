package com.napier.coursework;

import java.sql.Connection;
import java.sql.ResultSet;

import static com.napier.coursework.QueryHelper.getResultSet;

public class PopulationReport {

    //  The population of the world.
    public void getWorldPopulationDesc(Connection connection) {
        try {
            String query = "SELECT b.Name, a.Name, b.Population FROM country a INNER JOIN city b ON a.Capital = b.ID ORDER BY 3 DESC, 1";
            ResultSet rset = getResultSet(connection, query);
            // Check one is returned
            if (rset.next()) {
                String name = rset.getString("b.Name");
                System.out.println("Population is " + name);

            } else {
                System.out.println("Population not found.");

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Population details");

        }
    }


    // The population of a continent.
    public void getContinentPopulationDesc(Connection connection) {
        try {
            String query = "SELECT b.Name, a.Name, a.Continent, b.Population FROM country a INNER JOIN city b ON a.Capital = b.ID ORDER BY 3, 4 DESC, 1";
            ResultSet rset = getResultSet(connection, query);
            // Check one is returned
            if (rset.next()) {
                String name = rset.getString("b.Name");
                System.out.println("Population " + name);

            } else {
                System.out.println("Population not found.");

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Population details");

        }
    }
}
