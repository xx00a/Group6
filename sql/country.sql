#ID 1
SELECT country.Code,country.Name,country.Continent,country.Region,
       country.Population,
       c.name AS 'Capital'
FROM   country
           LEFT JOIN city c
                     ON country.capital = c.id
ORDER  BY country.population DESC;

#ID 2
SELECT country.Code,country.Name,country.Continent,country.Region,
       country.Population,
       c.name AS 'Capital'
FROM   country
           LEFT JOIN city c
                     ON country.capital = c.id
WHERE  country.continent LIKE 'North America'
ORDER  BY country.population DESC;

#ID 3
SELECT country.Code,country.Name,country.Continent,country.Region,
       country.Population,
       c.name AS 'Capital'
FROM   country
           LEFT JOIN city c
                     ON country.capital = c.id
WHERE  country.region LIKE 'South America'
ORDER  BY country.population DESC;

#ID 4
SELECT country.Code,country.Name,country.Continent,country.Region,
       country.Population,
       c.name AS 'Capital'
FROM   country
           LEFT JOIN city c
                     ON country.capital = c.id
ORDER  BY country.population DESC
    LIMIT  0, 5;

#ID 5
SELECT country.Code,country.Name,country.Continent,country.Region,
       country.Population,
       c.name AS 'Capital'
FROM   country
           LEFT JOIN city c
                     ON country.capital = c.id
WHERE  country.continent LIKE 'Asia'
ORDER  BY country.population DESC
    LIMIT  0, 5;

#ID 6
SELECT country.Code,country.Name,country.Continent,country.Region,
       country.Population,
       c.name AS 'Capital'
FROM   country
           LEFT JOIN city c
                     ON country.capital = c.id
WHERE  country.Region LIKE 'Eastern Asia'
ORDER  BY country.population DESC
    LIMIT  0, 5;