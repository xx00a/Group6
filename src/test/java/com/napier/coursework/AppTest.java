package com.napier.coursework;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.Connection;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest
{
    static App app;


    @BeforeAll
    static void init()
    {
        app = new App();

    }

    @Test
    void getNumberOfCities() {

        app.test();
    }




}
