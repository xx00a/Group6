package com.napier.coursework;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.napier.coursework.QueryHelper.getResultSet;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CityReportTest {
    CityReport cityReport = new CityReport();
    Connection connection;
    MySQLConnection mySQLConnection;

    @BeforeAll
    public void init() {
        mySQLConnection = new MySQLConnection();
        connection = mySQLConnection.connect("jdbc:mysql://localhost:3080/world?useSSL=false", "root", "example");
    }

    @Test
    public void getWorldCitiesByPopulationDescShouldReturnCorrectNumberOfCities() throws SQLException {
        String query = cityReport.getWorldCitiesByPopulationDescQuery();
        ResultSet resultSet = getResultSet(connection, query);
        List<City> cities = cityReport.parseCityList(resultSet);
        assertEquals(4079, cities.size(), "Cities of the world test.");
    }

    @AfterAll
    public void finish() {
        if (mySQLConnection != null) {
            mySQLConnection.disconnect(connection);
        }
    }

}