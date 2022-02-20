package com.napier.coursework;

import java.sql.*;

public class App {
    public static void main(String[] args) {

        // Connect to database
        MySQLConnection mySQLConnection = new MySQLConnection();

        Connection connection = mySQLConnection.connect();

        CityReport cityReport = new CityReport();
        // Display results
        cityReport.getWorldCitiesByPopulationDesc(connection);
        cityReport.getContinentCitiesByPopulationDesc(connection, "Africa");

        CapitalCityReport capitalCityReport = new CapitalCityReport();
        // Display results
        capitalCityReport.getWorldCapitalCitiesByPopulationDesc(connection);


        CountryReport countryReport = new CountryReport();
        // Display results
        countryReport.getWorldCountriesByPopulationDesc(connection);


        LanguagesReport languagesReport = new LanguagesReport();
        // Display results
        languagesReport.getWorldLanguagesByPopulationDesc(connection);


        PopulationReport populationReport = new PopulationReport();
        // Display results
        populationReport.getWorldPopulationDesc(connection);


        // Disconnect from database
        mySQLConnection.disconnect(connection);
    }
}