package com.napier.coursework;

import java.sql.Connection;
import java.sql.ResultSet;

import static com.napier.coursework.QueryHelper.getResultSet;

public class CityReport
{

    //   All the cities in the world organised by largest population to smallest.
    public void getWorldCitiesByPopulationDesc(Connection connection)
    {
        try {
            String query =   "SELECT city.Name AS City, country.Name AS Country, city.District, city.Population FROM city  INNER JOIN country  ON city.CountryCode = country.Code ORDER BY 4 DESC, 1";
            ResultSet rset = getResultSet(connection, query);
            // Check one is returned
            if (rset.next()) {
                String name = rset.getString("City");
                System.out.println("City name is " + name);

            } else {
                System.out.println("City  not found.");

            }
       } catch (Exception e) {
         System.out.println(e.getMessage());
         System.out.println("Failed to get City  details");

        }
    }

    // All the cities in a continent organised by largest population to smallest.
    public void getContinentCitiesByPopulationDesc(Connection connection)
    {
        try {
            String query =   "\n" +
                    "SELECT city.Name AS City, country.Name AS Country, city.District, country.Continent, city.Population FROM city  INNER JOIN country  ON city.CountryCode = country.Code ORDER BY 4, 5 DESC, 1";
            ResultSet rset = getResultSet(connection, query);
            // Check one is returned
            if (rset.next()) {
                String name = rset.getString("City");
                System.out.println("City name is " + name);

            } else {
                System.out.println("City  not found.");

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City  details");

        }
    }

    // All the cities in a region organised by largest population to smallest.
    public void getRegiondCitiesByPopulationDesc(Connection connection)
    {
        try {
            String query =   "\n" +
                    "SELECT city.Name AS City, country.Name AS Country, city.District, country.Region, city.Population FROM city  INNER JOIN country  ON city.CountryCode = country.Code ORDER BY 4, 5 DESC, 1";
            ResultSet rset = getResultSet(connection, query);
            // Check one is returned
            if (rset.next()) {
                String name = rset.getString("City");
                System.out.println("City name is " + name);

            } else {
                System.out.println("City  not found.");

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City  details");

        }
    }

}
