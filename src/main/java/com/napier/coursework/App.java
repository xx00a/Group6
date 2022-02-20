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

        CapitalCityReport capitalCityReport = new CapitalCityReport();
        // Display results
        capitalCityReport.getWorldCapitalCitiesByPopulationDesc(connection);


        CountryReport countryReport = new CountryReport();
        // Display results
        countryReport.getWorldCountriesByPopulationDesc(connection);


        LanguagesReport languagesReport = new LanguagesReport();
        // Display results
        languagesReport.getWorldLanguagesByPopulationDesc(connection);

*/
        PopulationReport populationReport = new PopulationReport();

        /****************** Population reports ******************/
        populationReport.getPopulationPerContinent(connection);
        populationReport.getPopulationPerRegion(connection);
        populationReport.getPopulationPerCountry(connection);
        populationReport.getPopulationOfWorld(connection);
        populationReport.getPopulationOfContinent(connection, "Asia");
        populationReport.getPopulationOfRegion(connection, "Baltic Countries");
        /*******************************************************/

        // Disconnect from database
        mySQLConnection.disconnect(connection);
    }
}