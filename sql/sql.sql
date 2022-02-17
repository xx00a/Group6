#All world population
SELECT Sum(country.Population) AS "World Population" From country;

#The population of a country.
SELECT  country.Name AS Country, country.Continent AS Continent, country.Region AS Region, country.Population  FROM country WHERE country.Name = "Nigeria";

#The population of a district.
SELECT city.District, Sum(city.Population) AS Population FROM city  WHERE city.District = "New York";

#The population of a city.
SELECT city.Name AS City, country.Name AS Country, city.District, city.Population FROM city  INNER JOIN country  ON city.CountryCode = country.Code WHERE city.name = "Lagos";

#the number of people who speak the following  languages from greatest number to smallest, including the percentage of the world population:
#Speaking Chinese.
SELECT countrylanguage.Language, Sum(country.Population) AS Speakers FROM countrylanguage  INNER JOIN country ON countrylanguage.CountryCode = country.Code WHERE countrylanguage.Language= "Chinese";

#Speaking English.
SELECT countrylanguage.Language, Sum(country.Population) AS Speakers  FROM countrylanguage  INNER JOIN country ON countrylanguage.CountryCode = country.Code WHERE countrylanguage.Language= "English";

#Speaking Hindi.
SELECT countrylanguage.Language, Sum(country.Population) AS Speakers  FROM countrylanguage  INNER JOIN country ON countrylanguage.CountryCode = country.Code WHERE countrylanguage.Language= "Hindi";

#Spanish.
SELECT countrylanguage.Language, Sum(country.Population) AS Speakers  FROM countrylanguage  INNER JOIN country ON countrylanguage.CountryCode = country.Code WHERE countrylanguage.Language= "Spanish";

#Arabic.
SELECT countrylanguage.Language, Sum(country.Population)  AS Speakers FROM countrylanguage  INNER JOIN country ON countrylanguage.CountryCode = country.Code WHERE countrylanguage.Language= "Arabic";

