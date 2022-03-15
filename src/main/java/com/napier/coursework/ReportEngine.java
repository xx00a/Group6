package com.napier.coursework;

/*
 * The Country report drives the functionality of a country report and outputs a selected report ID for display
 */

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import static com.napier.coursework.QueryHelper.getResultSet;


public class ReportEngine {

    // begin static references --->

    Hashtable<Integer, Integer> rID = new Hashtable<>();

    // load constants
    static int REPORT_CAPITAL_CITY = 1;
    static int REPORT_CITY = 2;
    static int REPORT_COUNTRY = 3;
    static int REPORT_LANGUAGES = 4;
    static int REPORT_POPULATION = 5;

    // sql queries
    String[] reportSQL;

    {
        reportSQL = new String[32];
        reportSQL[1] = """
                SELECT country.Code as 'YYY',country.Name,country.Continent,country.Region,
                       country.Population,
                       c.name AS 'Capital'
                FROM   country
                       LEFT JOIN city c
                              ON country.capital = c.id
                ORDER  BY country.population DESC;""";
    }

    private static String[] reportIndex;

    static {
        reportIndex = new String[32];
        reportIndex[1] = "All the countries in the world organised by largest population to smallest:";
        reportIndex[2] = "All the countries in a continent organised by largest population to smallest:";
        reportIndex[3] = "All the countries in a region organised by largest population to smallest:";
        reportIndex[4] = "The top N populated countries in the world where N is provided by the user:";
        reportIndex[5] = "The top N populated countries in a continent where N is provided by the user:";
        reportIndex[10] = "The top N populated countries in a region where N is provided by the user:";
    }


    // begin report generator
    private void createReport(int reportID, int reportClass, String queryText, Connection mySQLengine) throws SQLException {

        // let's insert our variable
        reportSQL[reportID].replaceAll("YYY",queryText);

        // create record set
        ResultSet rSet = getResultSet(mySQLengine, reportSQL[reportID]);

        // let's create our classes to start reporting
        switch (reportClass)
        {
            case 1:
                //REPORT_CAPITAL_CITY

                // create array reference
                ArrayList<CapitalCity> capitalCity = new ArrayList<>();

                // cycle through the record set return from mySQL
                while (rSet.next()) {

                    // create object
                    CapitalCity aCapitalCity = new CapitalCity();

                    // set values for array item
                    aCapitalCity.setCountry(rSet.getString("XXXXX"));

                    // add to record set so we can recall later
                    capitalCity.add(aCapitalCity);

                }

                // this will output to screen
                for (CapitalCity aCapitalCity : capitalCity) {
                    String stringCapitalCity =
                      String.format(" %-30s  %-30s  %-30s  %-30s  %-30s  %-30s ",
                           aCapitalCity.getCountry(),0,0,0,0,0);
                    System.out.println(stringCapitalCity);

                }

                // to-do on agreement: let's dump to a text file with HTML


                break;
            case 2:
                //REPORT_CITY
                break;
            case 3:
                //REPORT_COUNTRY
                break;
            case 4:
                //REPORT_LANGUAGES
                break;
            case 5:
                //REPORT_POPULATION

                break;

        }

    }


    // begin constructor
    public ReportEngine(int reportID, String argument, Connection mySQLc) throws SQLException {

        // variables
        int reportClass;

        // define report associations
        rID.put(1,REPORT_CAPITAL_CITY);
        rID.put(2,REPORT_CAPITAL_CITY);
        rID.put(3,REPORT_CAPITAL_CITY);
        rID.put(4,REPORT_CAPITAL_CITY);
        rID.put(5,REPORT_CAPITAL_CITY);
        rID.put(6,REPORT_CITY);
        rID.put(7,REPORT_CITY);
        rID.put(8,REPORT_CITY);
        rID.put(9,REPORT_CITY);
        rID.put(10,REPORT_CAPITAL_CITY);
        rID.put(11,REPORT_CITY);
        rID.put(12,REPORT_CITY);
        rID.put(13,REPORT_CITY);
        rID.put(14,REPORT_COUNTRY);
        rID.put(15,REPORT_COUNTRY);
        rID.put(16,REPORT_COUNTRY);
        rID.put(17,REPORT_COUNTRY);
        rID.put(18,REPORT_COUNTRY);
        rID.put(19,REPORT_COUNTRY);
        rID.put(20,REPORT_COUNTRY);
        rID.put(21,REPORT_LANGUAGES);
        rID.put(22,REPORT_LANGUAGES);
        rID.put(23,REPORT_LANGUAGES);
        rID.put(24,REPORT_LANGUAGES);
        rID.put(25,REPORT_LANGUAGES);
        rID.put(26,REPORT_POPULATION);
        rID.put(27,REPORT_POPULATION);
        rID.put(28,REPORT_POPULATION);
        rID.put(29,REPORT_POPULATION);
        rID.put(30,REPORT_POPULATION);
        rID.put(31,REPORT_POPULATION);

        // translate our class
        reportClass = rID.get(reportID);

        createReport(reportID,reportClass,argument,mySQLc);




        System.out.println(reportID);
        System.out.println();
        System.out.println(argument);

        // do things
    }


    private void createReport(Connection connection, String query) throws SQLException {
        getResultSet(connection, query);
    }
}
