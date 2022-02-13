package com.napier.coursework;

import java.sql.*;

public class App
{
    public static void main(String[] args) {

        // Connect to database
        MySQLConnection mySQLConnection = new MySQLConnection();

        Connection connection= mySQLConnection.connect();

        CityReport cityReport = new CityReport();
        // Display results
        cityReport.getWorldCitiesByPopulationDesc(connection);
        cityReport.getContinentCitiesByPopulationDesc(connection);
        cityReport.getRegiondCitiesByPopulationDesc(connection);

        CapitalCityReport capitalCityReport = new CapitalCityReport();
        // Display results
        capitalCityReport.getWorldCapitalCitiesByPopulationDesc(connection);
        capitalCityReport.getContinentCapitalCitiesByPopulationDesc(connection);

        CountryReport countryReport = new CountryReport();
        // Display results
        capitalCityReport.getWorldCapitalCitiesByPopulationDesc(connection);
        capitalCityReport.getContinentCapitalCitiesByPopulationDesc(connection);


        LanguagesReport languagesReport = new LanguagesReport();
        // Display results
        languagesReport.getWorldLanguagesByPopulationDesc(connection);
        languagesReport.getContinentLanguagesByPopulationDesc(connection);


        PopulationReport populationReport = new PopulationReport();
        populationReport.getWorldPopulationDesc(connection);
        populationReport.getContinentPopulationDesc(connection);

        // Disconnect from database
        mySQLConnection.disconnect(connection);
    }

}