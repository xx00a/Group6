package com.napier.coursework;

/*
 * SET08803 Coursework Application
 *
 */

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


        // Start of Country Series of Reports -->
        // Create new Country Report (from class)
        CountryReport countryReport = new CountryReport();

        //For now, we will use static references for N until interface is developed
        //This will cycle through Report IDs 1 to 6

        for (int x = 1; x < 7; x++) {
            // Call on getReport method to create individual report
            countryReport.getReport(x, connection);
        }
        // End of Country Series of Reports -->


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