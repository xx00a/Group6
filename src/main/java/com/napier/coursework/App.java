package com.napier.coursework;

import java.sql.*;

public class App {
    public static void main(String[] args) {

        // Connect to database
        MySQLConnection mySQLConnection = new MySQLConnection();

        Connection connection = mySQLConnection.connect();
/*
        CityReport cityReport = new CityReport();
        // Display results
        cityReport.getWorldCitiesByPopulationDesc(connection);
        cityReport.getContinentCitiesByPopulationDesc(connection);
        cityReport.getRegiondCitiesByPopulationDesc(connection);
*/
        // Create a new instance of the CapitalCityReport class
        CapitalCityReport capitalCityReport = new CapitalCityReport();
        // Display results for all the capital cities in the world organised by largest population to smallest.
        capitalCityReport.getAllCapitalCityPopulation(connection);
        // Display results for all the capital cities in a continent organised by largest population to smallest.
        capitalCityReport.getCapitalCityPopulationByContinent(connection);
        // Display results for all the capital cities in a region organised by largest population to smallest.
        capitalCityReport.getCapitalCityPopulationByRegion(connection);
        // Display results for the top N populated capital cities in the world where N is provided by the user.
        capitalCityReport.getTopNCapitalCities(connection, 10);
        // Display results for the top N populated capital cities in a continent where N is provided by the user.
        capitalCityReport.getTopNCapitalCitiesInOneContinent(connection, 10, "Asia");
        // Display results for the top N populated capital cities in a region where N is provided by the user.
        capitalCityReport.getTopNCapitalCitiesInOneRegion(connection, 10, "Western Europe");
        // Display results for the top N populated capital cities in all continents where N is provided by the user.
        capitalCityReport.getTopNCapitalCitiesInAllContinents(connection, 10);
        // Display results for the top N populated capital cities in all global regions where N is provided by the user.
        capitalCityReport.getTopNCapitalCitiesInAllRegions(connection, 10);

/*
        CountryReport countryReport = new CountryReport();
        // Display results
        countryReport.getWorldCountriesByPopulationDesc(connection);


        LanguagesReport languagesReport = new LanguagesReport();
        // Display results
        languagesReport.getWorldLanguagesByPopulationDesc(connection);


        PopulationReport populationReport = new PopulationReport();
        // Display results
        populationReport.getWorldPopulationDesc(connection);
*/
        // Disconnect from database
        mySQLConnection.disconnect(connection);
    }
}