package com.napier.coursework;

import java.sql.Connection;
import java.sql.SQLException;

public class LanguagesReport
{


//    private void createReport(Connection connection, String query) throws SQLException {
//        ResultSet rset = getResultSet(connection, query);
//        ArrayList<City> cities = new ArrayList<City>();
//
//        while (rset.next()) {
//            City city = new City();
//            city.name = rset.getString("City");
//            city.country = rset.getString("Country");
//            city.district = rset.getString("city.District");
//            city.population = rset.getInt("city.Population");
//            cities.add(city);
//        }
//        System.out.println(String.format(" %-30s  %-30s  %-30s  %-30s ", "City", "Country", "District", "Population"));
//        for (City city : cities) {
//            String cityString =
//                    String.format(" %-30s  %-30s  %-30s  %-30s ",
//                            city.name, city.country, city.district, city.population);
//            System.out.println(cityString);
//        }
//    }

    //   All the languages in the world organised by largest population to smallest.
    public void getWorldLanguagesByPopulationDesc(Connection connection)
        {
            try {
                String query = "SELECT countrylanguage.Language, Sum(country.Population) AS Speakers, AVG(countrylanguage.Percentage) AS Percentage FROM countrylanguage  INNER JOIN country ON countrylanguage.CountryCode = country.Code\n" +
                        "        WHERE countrylanguage.Language= \"Chinese\" OR countrylanguage.Language=\"English\" OR countrylanguage.Language=\"Hindi\" OR countrylanguage.Language=\"Spanish\"\n" +
                        "        OR countrylanguage.Language=\"Arabic\" GROUP BY countrylanguage.Language ORDER BY Speakers DESC;";
                System.out.println("\n All the languages in the world organised by largest population to smallest. \n");
                createReport(connection, query);

            } catch (SQLException e) {
                System.out.println(e.getMessage());
                System.out.println("Failed to get language details");

            }
        }
}

