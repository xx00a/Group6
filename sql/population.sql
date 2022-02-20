
-- The population of people, people living in cities, and people not living in cities in each continent.

SELECT country.continent,
       (SUM(DISTINCT(country.population)) + SUM(city. population)) AS 'Total Population',
        SUM(DISTINCT(country.population)) AS 'NOT in cities',
        (((SUM(DISTINCT(country.population))) / (SUM(DISTINCT(country.population)) + SUM(city.population)))*100) AS 'NOT in cities(%)',
        SUM(city. population) AS 'IN cities',
        (((SUM(DISTINCT(city.population))) / (SUM(DISTINCT(country.population)) + SUM(city.population)))*100) AS 'IN cities(%)'
        FROM country JOIN city ON city.countrycode = country.code
        GROUP by country.continent;

-- The population of people, people living in cities, and people not living in cities in each region.

SELECT country.region,
       (SUM(DISTINCT(country.population)) + SUM(city. population)) AS 'Total Population',
        SUM(DISTINCT(country.population)) AS 'NOT in cities',
        (((SUM(DISTINCT(country.population))) / (SUM(DISTINCT(country.population)) + SUM(city.population)))*100) AS 'NOT in cities(%)',
        SUM(city. population) AS 'IN cities',
        (((SUM(DISTINCT(city.population))) / (SUM(DISTINCT(country.population)) + SUM(city.population)))*100) AS 'IN cities(%)'
        FROM country JOIN city ON city.countrycode = country.code
        GROUP by country.region;

-- The population of people, people living in cities, and people not living in cities in each country.

SELECT country.name,
       (SUM(DISTINCT(country.population)) + SUM(city. population)) AS 'Total Population',
        SUM(DISTINCT(country.population)) AS 'NOT in cities',
        (((SUM(DISTINCT(country.population))) / (SUM(DISTINCT(country.population)) + SUM(city.population)))*100) AS 'NOT in cities(%)',
        SUM(city. population) AS 'IN cities',
        (((SUM(DISTINCT(city.population))) / (SUM(DISTINCT(country.population)) + SUM(city.population)))*100) AS 'IN cities(%)'
        FROM country JOIN city ON city.countrycode = country.code
        GROUP by country.name;

-- The population of the world.

SELECT (SUM(DISTINCT(country.population)) + SUM(city. population)) AS 'Total Population Of World'
        FROM country JOIN city ON city.countrycode = country.code;

-- The population of a continent.

SELECT (SUM(DISTINCT(country.population)) + SUM(city. population)) AS 'Population of a continent'
FROM country JOIN city ON city.countrycode = country.code WHERE country.continent = '';

-- The population of a region.

SELECT (SUM(DISTINCT(country.population)) + SUM(city. population)) AS 'Population of a region'
FROM country JOIN city ON city.countrycode = country.code WHERE country.region = '';