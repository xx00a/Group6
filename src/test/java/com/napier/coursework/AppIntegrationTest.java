package com.napier.coursework;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest
{

    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
        App.sqlConnect = MySQLConnection.connect("jdbc:mysql://localhost:33060/world?useSSL=false", "root", "example");
    }

    @Test
    void getReportTestDefaultValues() {
        assertNotNull(app.getReport(1, "", "1"));
    }

    @AfterAll
    static void disconnectDB(){
      MySQLConnection.disconnect(App.sqlConnect);
    }
}
