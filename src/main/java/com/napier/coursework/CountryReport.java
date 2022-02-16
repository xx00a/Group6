package com.napier.coursework;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.napier.coursework.QueryHelper.getResultSet;

public class CountryReport {

    private void createReport(Connection connection, String query) throws SQLException {
        ResultSet rset = getResultSet(connection, query);
        ArrayList<City> cities = new ArrayList<City>();

        while (rset.next()) {
            City city = new City();
            city.name = rset.getString("City");
            city.country = rset.getString("Country");
            city.district = rset.getString("city.District");
            city.population = rset.getInt("city.Population");
            cities.add(city);
        }
        System.out.println(String.format(" %-30s  %-30s  %-30s  %-30s ", "City", "Country", "District", "Population"));
        for (City city : cities) {
            String cityString =
                    String.format(" %-30s  %-30s  %-30s  %-30s ",
                            city.name, city.country, city.district, city.population);
            System.out.println(cityString);
        }
    }

    //   All the countries in the world organised by largest population to smallest.
    public void getWorldCountriesByPopulationDesc(Connection connection) {
        {
            try {
                String query = "SELECT city.Name AS City, country.Name AS Country, city.District, city.Population FROM city  INNER JOIN country  ON city.CountryCode = country.Code ORDER BY 4 DESC, 1 LIMIT 5";
                System.out.println("\n All the countries in the world organised by largest population to smallest. \n");
                createReport(connection, query);

            } catch (SQLException e) {
                System.out.println(e.getMessage());
                System.out.println("Failed to get countries details");

            }
        }
    }
}