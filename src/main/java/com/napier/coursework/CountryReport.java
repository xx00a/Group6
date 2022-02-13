package com.napier.coursework;

import java.sql.Connection;
import java.sql.ResultSet;

import static com.napier.coursework.QueryHelper.getResultSet;

public class CountryReport {

    //   All the countries in the world organised by largest population to smallest.
    public void getWorldCountriesByPopulationDesc(Connection connection)
    {
        try {
            String query =   "SELECT b.Name, a.Name, b.Population FROM country a INNER JOIN city b ON a.Capital = b.ID ORDER BY 3 DESC, 1";
            ResultSet rset = getResultSet(connection, query);
            // Check one is returned
            if (rset.next()) {
                String name = rset.getString("b.Name");
                System.out.println("Country name is " + name);

            } else {
                System.out.println("Country not found.");

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Country details");

        }
    }




    // All the countries in a continent organised by largest population to smallest.
    public void getContinentCountriesByPopulationDesc(Connection connection)
    {
        try {
            String query =   "SELECT b.Name, a.Name, a.Continent, b.Population FROM country a INNER JOIN city b ON a.Capital = b.ID ORDER BY 3, 4 DESC, 1";
            ResultSet rset = getResultSet(connection, query);
            // Check one is returned
            if (rset.next()) {
                String name = rset.getString("b.Name");
                System.out.println("Country name is " + name);

            } else {
                System.out.println("Country not found.");

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Country details");

        }
    }
}
