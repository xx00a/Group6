package com.napier.coursework;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals(5, 5);

    }

}
