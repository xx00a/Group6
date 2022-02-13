package com.napier.coursework;

import java.sql.Connection;
import java.sql.ResultSet;

import static com.napier.coursework.QueryHelper.getResultSet;

public class CapitalCityReport {


    //    All the capital cities in the world organised by largest population to smallest.
    public void getWorldCapitalCitiesByPopulationDesc(Connection connection)
    {
        try {
            String query =   "SELECT b.Name, a.Name, b.Population FROM country a INNER JOIN city b ON a.Capital = b.ID ORDER BY 3 DESC, 1";
            ResultSet rset = getResultSet(connection, query);
            // Check one is returned
            if (rset.next()) {
                String name = rset.getString("b.Name");
                System.out.println("Capital City name is " + name);

            } else {
                System.out.println("Capital City not found.");

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Capital City details");

        }
    }




    // All the capital cities in a continent organised by largest population to smallest.
    public void getContinentCapitalCitiesByPopulationDesc(Connection connection)
    {
        try {
            String query =   "SELECT b.Name, a.Name, a.Continent, b.Population FROM country a INNER JOIN city b ON a.Capital = b.ID ORDER BY 3, 4 DESC, 1";
            ResultSet rset = getResultSet(connection, query);
            // Check one is returned
            if (rset.next()) {
                String name = rset.getString("b.Name");
                System.out.println("Capital City name is " + name);

            } else {
                System.out.println("Capital City not found.");

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Capital City  details");

        }
    }


}
