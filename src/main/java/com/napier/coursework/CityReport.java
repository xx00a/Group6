package com.napier.coursework;

import java.sql.Connection;
import java.sql.ResultSet;

import static com.napier.coursework.QueryHelper.getResultSet;

public class CityReport
{

    public void getCity(int ID, Connection connection) {
        try {
            // Create string for SQL statement
            String query = "SELECT Name  FROM city WHERE ID = " + ID;
            ResultSet rset = getResultSet(connection, query);
            // Check one is returned
            if (rset.next()) {
                String name = rset.getString("Name");
                System.out.println("City name is " + name);

            } else {
                System.out.println("City  not found.");

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City  details");

        }
    }


    //    All the cities in the world organised by largest population to smallest.
    public void getAllWorldCitiesByPopulationDesc()
    {
        
    }



}
