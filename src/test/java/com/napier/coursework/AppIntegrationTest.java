package com.napier.coursework;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;
import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AppIntegrationTest
{

    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
        App.sqlConnect = MySQLConnection.connect("jdbc:mysql://localhost:33060/world?allowPublicKeyRetrieval=true&useSSL=false", "root", "example");
    }

    @Test
    void getDataFromDatabase()
    {

    }
    @Test
    void getReport(){

    }

    @Test
    void produceQueryHome(){

    }

    @AfterAll
    static void disconnectDB()
    {
      MySQLConnection.disconnect(App.sqlConnect);
    }
}
