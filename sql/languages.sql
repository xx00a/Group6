#All world population
SELECT Sum(country.Population) AS "World Population" From country;

#The population of a country.
SELECT  country.Name AS Country, country.Continent AS Continent, country.Region AS Region, country.Population  FROM country WHERE country.Name = "Nigeria";
SELECT  country.Name AS Country, country.Continent AS Continent, country.Region AS Region, country.Population  FROM country ORDER BY country.Name;
SELECT country.Name AS 'Name', (SUM(DISTINCT (country.population))) AS 'Total Population'
FROM country
         JOIN city ON city.countrycode = country.code
WHERE country.Name = "Nigeria";
#The population of a district.
SELECT city.District, Sum(city.Population) AS Population FROM city  WHERE city.District = "New York";
SELECT city.District, Sum(city.Population) AS Population FROM city  GROUP BY city.District;
SELECT city.District AS 'Name', (SUM(DISTINCT (country.population))) AS 'Total Population'
FROM country
         JOIN city ON city.countrycode = country.code
WHERE city.District = "Taipei";
#The population of a city.
SELECT city.Name AS City, country.Name AS Country, city.District, city.Population FROM city  INNER JOIN country  ON city.CountryCode = country.Code WHERE city.name = "Lagos";
SELECT city.Name AS City, country.Name AS Country, city.District, city.Population FROM city  INNER JOIN country  ON city.CountryCode = country.Code ORDER BY city.Name;
SELECT city.Name AS 'Name', (SUM(DISTINCT (country.population))) AS 'Total Population'
FROM country
         JOIN city ON city.countrycode = country.code
WHERE city.Name = "Lagos";
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

SELECT countrylanguage.Language, Sum(country.Population) AS Speakers FROM countrylanguage  INNER JOIN country ON
        countrylanguage.CountryCode = country.Code WHERE countrylanguage.Language= "Chinese" OR countrylanguage.Language="English" OR countrylanguage.Language="Hindi" OR countrylanguage.Language="Spanish"
        OR countrylanguage.Language="Arabic" GROUP BY countrylanguage.Language ORDER BY Speakers DESC;

SELECT countrylanguage.Language, Sum(country.Population) AS Speakers, AVG(countrylanguage.Percentage) AS Percentage FROM countrylanguage  INNER JOIN country ON countrylanguage.CountryCode = country.Code
        WHERE countrylanguage.Language= "Chinese" OR countrylanguage.Language="English" OR countrylanguage.Language="Hindi" OR countrylanguage.Language="Spanish"
        OR countrylanguage.Language="Arabic" GROUP BY countrylanguage.Language ORDER BY Speakers DESC;

WITH data as (
    SELECT countrylanguage.Language AS Language, Sum(country.Population) AS Population,
           Sum(countrylanguage.Percentage/100*country.Population) as Speakers
    FROM countrylanguage
             INNER JOIN country ON countrylanguage.CountryCode = country.Code
    WHERE countrylanguage.Language= "Chinese" OR countrylanguage.Language="English"
       OR countrylanguage.Language="Hindi" OR countrylanguage.Language="Spanish"
       OR countrylanguage.Language="Arabic"
    GROUP BY countrylanguage.Language ORDER BY Speakers DESC)
SELECT *, (Speakers/(SELECT Sum(country.Population) from country))*100 as Percentage
FROM data GROUP BY Language;