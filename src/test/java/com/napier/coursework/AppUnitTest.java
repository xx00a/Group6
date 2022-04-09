package com.napier.coursework;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class AppUnitTest
{

    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
        App.sqlConnect = MySQLConnection.connect("jdbc:mysql://localhost:33060/world?allowPublicKeyRetrieval=true&useSSL=false", "root", "example");

    }

    @Test
    void getNumberOfCities() throws SQLException, ClassNotFoundException {

        String htmlResult = app.getReport(1, "", "10");
        assertTrue(htmlResult.contains("China"));

    }

    @AfterAll
    static void disconnectDB(){
        MySQLConnection.disconnect(App.sqlConnect);
    }
}
