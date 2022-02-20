package com.napier.coursework;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.napier.coursework.QueryHelper.getResultSet;

public class PopulationReport
{

    private void createReport(Connection connection, String query, String property) throws SQLException {
        ResultSet rset = getResultSet(connection, query);
        ArrayList<Population> populations = new ArrayList<>();

        while (rset.next()) {
            Population population = new Population();
            population.name = rset.getString(property);
            population.totalPopulaton = rset.getLong("Total Population");
            population.notInCities = rset.getLong("NOT in cities");
            population.notInCitiesPercentage = rset.getInt("NOT in cities(%)");
            population.inCities = rset.getLong("IN cities");
            population.inCitiesPercentage = rset.getInt("IN cities(%)");
            populations.add(population);
        }
        System.out.println(String.format(" %-30s  %-30s  %-30s  %-30s  %-30s  %-30s ", "Name", "Total Population", "NOT in cities", "NOT in cities(%)", "IN cities", "IN cities(%)"));
        for (Population population : populations) {
            String populationString =
                    String.format(" %-30s  %-30s  %-30s  %-30s  %-30s  %-30s ",
                            population.name, population.totalPopulaton, population.notInCities, population.notInCitiesPercentage, population.inCities, population.inCitiesPercentage);
            System.out.println(populationString);
        }
    }

    //  The population of the world.
    public void getPopulationPerContinent(Connection connection) {
        try {
            String query = "SELECT country.continent,\n" +
                    "(SUM(DISTINCT(country.population)) + SUM(city. population)) AS 'Total Population',\n" +
                    "SUM(DISTINCT(country.population)) AS 'NOT in cities',\n" +
                    "(((SUM(DISTINCT(country.population))) / (SUM(DISTINCT(country.population)) + SUM(city.population)))*100) AS 'NOT in cities(%)',\n" +
                    "SUM(city. population) AS 'IN cities',\n" +
                    "(((SUM(DISTINCT(city.population))) / (SUM(DISTINCT(country.population)) + SUM(city.population)))*100) AS 'IN cities(%)'\n" +
                    "FROM country JOIN city ON city.countrycode = country.code\n" +
                    "GROUP by country.continent;";
            System.out.println("\n The population of people, people living in cities, and people not living in cities in each continent. \n");
            createReport(connection, query,"country.continent");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");

        }
    }

    public void getPopulationPerRegion(Connection connection) {
        try {
            String query = "SELECT country.region,\n" +
                    "(SUM(DISTINCT(country.population)) + SUM(city. population)) AS 'Total Population',\n" +
                    "SUM(DISTINCT(country.population)) AS 'NOT in cities',\n" +
                    "(((SUM(DISTINCT(country.population))) / (SUM(DISTINCT(country.population)) + SUM(city.population)))*100) AS 'NOT in cities(%)',\n" +
                    "SUM(city. population) AS 'IN cities',\n" +
                    "(((SUM(DISTINCT(city.population))) / (SUM(DISTINCT(country.population)) + SUM(city.population)))*100) AS 'IN cities(%)'\n" +
                    "FROM country JOIN city ON city.countrycode = country.code\n" +
                    "GROUP by country.region;";
            System.out.println("\n The population of people, people living in cities, and people not living in cities in each region. \n");
            createReport(connection, query,"country.region");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");

        }
    }

    public void getPopulationPerCountry(Connection connection) {
        try {
            String query = "SELECT country.name,\n" +
                    "(SUM(DISTINCT(country.population)) + SUM(city. population)) AS 'Total Population',\n" +
                    "SUM(DISTINCT(country.population)) AS 'NOT in cities',\n" +
                    "(((SUM(DISTINCT(country.population))) / (SUM(DISTINCT(country.population)) + SUM(city.population)))*100) AS 'NOT in cities(%)',\n" +
                    "SUM(city. population) AS 'IN cities',\n" +
                    "(((SUM(DISTINCT(city.population))) / (SUM(DISTINCT(country.population)) + SUM(city.population)))*100) AS 'IN cities(%)'\n" +
                    "FROM country JOIN city ON city.countrycode = country.code\n" +
                    "GROUP by country.name;";
            System.out.println("\n The population of people, people living in cities, and people not living in cities in each country. \n");
            createReport(connection, query,"country.name");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");

        }
    }
}