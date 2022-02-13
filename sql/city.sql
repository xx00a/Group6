
-- All the cities in the world organised by largest population to smallest.

SELECT city.Name AS City, country.Name AS Country, city.District, city.Population FROM city  INNER JOIN country  ON city.CountryCode = country.Code ORDER BY 4 DESC, 1;

-- All the cities in a continent organised by largest population to smallest.

SELECT city.Name AS City, country.Name AS Country, city.District, country.Continent, city.Population FROM city  INNER JOIN country  ON city.CountryCode = country.Code ORDER BY 4, 5 DESC, 1;

-- All the cities in a region organised by largest population to smallest.

SELECT city.Name AS City, country.Name AS Country, city.District, country.Region, city.Population FROM city  INNER JOIN country  ON city.CountryCode = country.Code ORDER BY 4, 5 DESC, 1;

-- All the cities in a country organised by largest population to smallest.

SELECT city.Name AS City, country.Name AS Country, city.District, city.Population FROM city  INNER JOIN country  ON city.CountryCode = country.Code ORDER BY 2, 4 DESC, 1;

-- All the cities in a district organised by largest population to smallest.

SELECT city.Name AS City, country.Name AS Country, city.District, city.Population FROM city  INNER JOIN country  ON city.CountryCode = country.Code ORDER BY 3, 4 DESC, 1;

-- The top N populated cities in the world where N is provided by the user.

SELECT city.Name AS City, country.Name AS Country, city.District, city.Population FROM city  INNER JOIN country  ON city.CountryCode = country.Code ORDER BY 4 DESC, 1 LIMIT 20;

-- The top N populated cities in a continent where N is provided by the user.

SELECT city.Name AS City, country.Name AS Country, city.District, country.Continent, city.Population FROM city  INNER JOIN country  ON city.CountryCode = country.Code WHERE country.Continent = "Africa" ORDER BY  5 DESC, 1 LIMIT 20;

-- The top N populated cities in a region where N is provided by the user.

SELECT city.Name AS City, country.Name AS Country, city.District, country.Region, city.Population FROM city  INNER JOIN country  ON city.CountryCode = country.Code WHERE country.Region = "Caribbean" ORDER BY 5 DESC, 1 LIMIT 20;

-- The top N populated cities in a country where N is provided by the user.

SELECT city.Name AS City, country.Name AS Country, city.District, city.Population FROM city  INNER JOIN country  ON city.CountryCode = country.Code WHERE country.Name = "Poland" ORDER BY 4 DESC, 1 LIMIT 20;

-- The top N populated cities in a district where N is provided by the user.

SELECT city.Name AS City, country.Name AS Country, city.District, city.Population FROM city  INNER JOIN country  ON city.CountryCode = country.Code WHERE city.District = "Zachodnio-Pomorskie" ORDER BY 4 DESC, 1 LIMIT 20;