package com.napier.coursework;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.napier.coursework.QueryHelper.getResultSet;


public class CityReport {


    //Method  handles report displaying
    private void createReport(Connection connection, String query) throws SQLException {
        // Create result set containing the query data
        ResultSet rset = getResultSet(connection, query);
        // Create a collection  to hold all the data for each instance of City
        ArrayList<City> cities = new ArrayList<City>();
        // Populate capitalCities with all the data for each instance of CapitalCity
        while (rset.next()) {
            City city = new City();
            city.setName(rset.getString("City"));
            city.setCountry(rset.getString("Country"));
            city.setDistrict(rset.getString("city.District"));
            city.setPopulation(rset.getInt("city.Population"));
            cities.add(city);
        }
        // Display report title
        System.out.println(String.format(" %-30s  %-30s  %-30s  %-30s ", "City", "Country", "District", "Population"));
        // Fetch each city data from the  arraylist, format them then display the data
        for (City city : cities) {
            String cityString =
                    String.format(" %-30s  %-30s  %-30s  %-30s ",
                            city.getName(), city.getCountry(), city.getDistrict(), city.getPopulation());
            // Display report body
            System.out.println(cityString);
        }
    }


    //Method  handles report displaying
    public List<City> parseCityList(ResultSet resultSet) throws SQLException {
        // Create a collection  to hold all the data for each instance of City
        ArrayList<City> cities = new ArrayList<City>();
        // Populate capitalCities with all the data for each instance of CapitalCity
        while (resultSet.next()) {
            City city = new City();
            city.setName(resultSet.getString("City"));
            city.setCountry(resultSet.getString("Country"));
            city.setDistrict(resultSet.getString("city.District"));
            city.setPopulation(resultSet.getInt("city.Population"));
            cities.add(city);
        }

        return cities;
    }

    private void displayReport(List<City> cities) {
        // Display report title
        System.out.println(String.format(" %-30s  %-30s  %-30s  %-30s ", "City", "Country", "District", "Population"));
        // Fetch each city data from the  arraylist, format them then display the data
        for (City city : cities) {
            String cityString =
                    String.format(" %-30s  %-30s  %-30s  %-30s ",
                            city.getName(), city.getCountry(), city.getDistrict(), city.getPopulation());
            // Display report body
            System.out.println(cityString);
        }
    }


    //   All the cities in the world organised by largest population to smallest.
    public String getWorldCitiesByPopulationDescQuery() {
        return "SELECT city.Name AS City, country.Name AS Country, city.District, city.Population FROM city  INNER JOIN country  ON city.CountryCode = country.Code ORDER BY 4 DESC";

    }


    public void createGetWorldCitiesByPopulationDescReport(Connection connection) {
        try {
            String query = getWorldCitiesByPopulationDescQuery();
            // Create result set containing the query data

            ResultSet resultSet = getResultSet(connection, query);
            List<City> cities = parseCityList(resultSet);
            System.out.println(" \n All the cities in the world organised by largest population to smallest. \n");
            displayReport(cities);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
        }


    }


    // All the cities in a continent organised by largest population to smallest.
    public void getContinentCitiesByPopulationDesc(Connection connection, String continent) {
        try {
            //Create the query string
            String query = "\n" +
                    "SELECT city.Name AS City, country.Name AS Country, city.District, city.Population FROM city  INNER JOIN country  ON city.CountryCode = country.Code WHERE country.Continent = '%s' ORDER BY 4 DESC, 1".formatted(continent);
            //Display report topic
            System.out.println(" \n All the cities in " + continent + " organised by largest population to smallest. \n");
            createReport(connection, query);
            // Handle errors
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");

        }
    }


    // All the cities in a region organised by largest population to smallest.
    public void getRegionCitiesByPopulationDesc(Connection connection, String region) {
        try {
            //Create the query string
            String query = "\n" +
                    "SELECT city.Name AS City, country.Name AS Country, city.District, city.Population FROM city  INNER JOIN country  ON city.CountryCode = country.Code WHERE country.Region = '%s' ORDER BY 4 DESC, 1".formatted(region);
            //Display report topic
            System.out.println("\n All the cities in " + region + " organised by largest population to smallest.\n");
            createReport(connection, query);
            // Handle errors
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");

        }

    }

    //All the cities in a country organised by largest population to smallest.
    public void getCountryCitiesByPopulationDesc(Connection connection, String country) {
        try {
            //Create the query string
            String query = "\n" +
                    "SELECT city.Name AS City, country.Name AS Country, city.District, city.Population FROM city  INNER JOIN country  ON city.CountryCode = country.Code WHERE country.Name = '%s' ORDER BY 4 DESC, 1".formatted(country);
            //Display report topic
            System.out.println("\n All the cities in " + country + " organised by largest population to smallest.\n");
            createReport(connection, query);
            // Handle errors
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
        }
    }

    // All the cities in a district organised by largest population to smallest.
    public void getDistrictCitiesByPopulationDesc(Connection connection, String district) {
        try {
            //Create the query string
            String query = "\n" +
                    "SELECT city.Name AS City, country.Name AS Country, city.District, city.Population FROM city  INNER JOIN country  ON city.CountryCode = country.Code WHERE city.District = '%s' ORDER BY 4 DESC, 1".formatted(district);
            //Display report topic
            System.out.println("\n All the cities in " + district + " organised by largest population to smallest.\n");
            createReport(connection, query);
            // Handle errors
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
        }
    }
// The top N populated cities in the world where N is provided by the user.

    public void getNumberWorldCitiesByPopulationDesc(Connection connection, int numberOfCities) {
        try {
            //Create the query string
            String query = "\n" +
                    "SELECT city.Name AS City, country.Name AS Country, city.District, city.Population FROM city  INNER JOIN country  ON city.CountryCode = country.Code ORDER BY 4 DESC LIMIT %s".formatted(numberOfCities);
            //Display report topic
            System.out.println("\n" + numberOfCities + " cities in the World organised by largest population to smallest.\n");
            createReport(connection, query);
            // Handle errors
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
        }
    }

// The top N populated cities in a continent where N is provided by the user.

    public void getNumberContinentCitiesByPopulationDesc(Connection connection, String continent, int numberOfCities) {
        try {
            //Create the query string
            String query = "\n" +
                    "SELECT city.Name AS City, country.Name AS Country, city.District, city.Population FROM city  INNER JOIN country  ON city.CountryCode = country.Code WHERE country.Continent = '%s' ORDER BY 4 DESC LIMIT %s".formatted(continent, numberOfCities);
            //Display report topic
            System.out.println(" \n" + numberOfCities + " cities in " + continent + " organised by largest population to smallest. \n");
            createReport(connection, query);
            // Handle errors
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");

        }
    }


// The top N populated cities in a region where N is provided by the user.

    public void getNumberRegionCitiesByPopulationDesc(Connection connection, String region, int numberOfCities) {
        try {
            //Create the query string
            String query = "\n" +
                    "SELECT city.Name AS City, country.Name AS Country, city.District, city.Population FROM city  INNER JOIN country  ON city.CountryCode = country.Code WHERE country.Region = '%s' ORDER BY 4 DESC LIMIT %s ".formatted(region, numberOfCities);
            //Display report topic
            System.out.println("\n" + numberOfCities + " cities in " + region + " organised by largest population to smallest.\n");
            createReport(connection, query);
            // Handle errors
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");

        }

    }

// The top N populated cities in a country where N is provided by the user.

    public void getNumberCountryCitiesByPopulationDesc(Connection connection, String country, int numberOfCities) {
        try {
            //Create the query string
            String query = "\n" +
                    "SELECT city.Name AS City, country.Name AS Country, city.District, city.Population FROM city  INNER JOIN country  ON city.CountryCode = country.Code WHERE country.Name = '%s' ORDER BY 4 DESC LIMIT %s".formatted(country, numberOfCities);
            //Display report topic
            System.out.println("\n " + numberOfCities + " cities in " + country + " organised by largest population to smallest.\n");
            createReport(connection, query);
            // Handle errors
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
        }
    }


//  The top N populated cities in a district where N is provided by the user.

    public void getNumberDistrictCitiesByPopulationDesc(Connection connection, String district, int numberOfCities) {
        try {
            //Create the query string
            String query = "\n" +
                    "SELECT city.Name AS City, country.Name AS Country, city.District, city.Population FROM city  INNER JOIN country  ON city.CountryCode = country.Code WHERE city.District = '%s' ORDER BY 4 DESC LIMIT %S".formatted(district, numberOfCities);
            //Display report topic
            System.out.println("\n " + numberOfCities + " in " + district + " organised by largest population to smallest.\n");
            createReport(connection, query);
            // Handle errors
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
        }
    }


}