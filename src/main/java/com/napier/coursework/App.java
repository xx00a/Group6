package com.napier.coursework;

import java.sql.*;
import java.util.ArrayList;

public class App
{
    public static void main(String[] args) {

        // Create new Application
        App a = new App();

        // Connect to database
        a.connect();

        // Get and display all capital cities
        a.displayCapitalCities(a.getCapitalCities());

        // Disconnect from database
        a.disconnect();

    }

    /**
     * Displays all capital cities in every country of the world
     */
    public void displayCapitalCities(ArrayList<CapitalCity> capitalCities)
    {
        if (capitalCities != null)
        {
            System.out.print("Capital City"+"\t"+"Country"+"\t"+"Population"+"\n");
            for (CapitalCity capCity: capitalCities) {
                System.out.print(capCity.getName()+"\t"+capCity.getCountry()+"\t"+capCity.getPopulation()+"\n");
            }
        }
    }

    /**
     * Gets capital cities in every country of the world
     * @return A list of all capital cities in the world
     */
    public ArrayList<CapitalCity> getCapitalCities()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT b.Name as 'Capital_City', a.Name, b.Population "
                    + "FROM country a INNER JOIN city b ON a.Capital = b.ID "
                    + "ORDER BY 3 DESC, 1";
            // Execute SQL statement
            ResultSet resSet = stmt.executeQuery(strSelect);
            // Extract capital city information
            ArrayList<CapitalCity> capitalCities = new ArrayList<CapitalCity>();
            while (resSet.next())
            {
                CapitalCity capCity = new CapitalCity();
                capCity.setName(resSet.getString("Capital_City"));
                capCity.setCountry(resSet.getString("a.Name"));
                capCity.setPopulation(resSet.getInt("b.Population"));
                capitalCities.add(capCity);
            }
            return capitalCities;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get salary details");
            return null;
        }
    }

    public String getCity(int ID)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =  "SELECT Name  FROM city WHERE ID = " + ID;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Check one is returned
            if (rset.next())
            {
                String name = rset.getString("Name");
                System.out.println("City name is " + name);
                return name;
            }
            else {
                System.out.println("City  not found.");
                return null;
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City  details");
            return null;
        }
    }
    // Connection to MySQL database.
    private Connection con = null;
    public void connect()
    {
        try
        {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    // Disconnect from the MySQL database.
    public void disconnect()
    {
        if (con != null)
        {
            try
            {
                // Close connection
                con.close();
                System.out.println("Database connection closed.");
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }
    }

}