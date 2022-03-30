package com.napier.coursework;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest
{

    static App app;


    @BeforeAll
    static void init()
    {
        app = new App();
        App.sqlConnect = MySQLConnection.connect("jdbc:mysql://localhost:3306/world?useSSL=false", "root", "example");

    }

    @Test
    void getNumberOfCities() throws SQLException, ClassNotFoundException {

        String output = app.getReport(1, "", "1");
        assertEquals(5, 5);

    }



    @AfterAll
    static void disconnectDB(){
        MySQLConnection.disconnect(App.sqlConnect);
    }
}
