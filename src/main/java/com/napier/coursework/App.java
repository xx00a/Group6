package com.napier.coursework;

public class App {

    //global variables
    private mySQLConnection SQL;

    public void CreateConnection() {

        SQL = new mySQLConnection("localhost:8888");
    }

    public static void main(String[] args)
    {

        /*
         * This the main method for the Group 6 application specified in the SET08803 Coursework
         */


        System.out.println("Boo yah!");
    }

}
