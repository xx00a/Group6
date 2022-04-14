package com.napier.coursework;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AppUnitTest
{

    @Test
    void getReportByIdShouldReturnCorrectReport() {
        Reports report =  ReportEngine.getReportById(1);
        assertEquals(Reports.REPORT_ALL_COUNTRIES_BY_POPULATION_DESC, report);
    }

}
