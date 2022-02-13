
-- All the capital cities in the world organised by largest population to smallest.
SELECT b.Name, a.Name, b.Population FROM country a INNER JOIN city b ON a.Capital = b.ID ORDER BY 3 DESC, 1;

-- All the capital cities in a continent organised by largest population to smallest.
SELECT b.Name, a.Name, a.Continent, b.Population FROM country a INNER JOIN city b ON a.Capital = b.ID ORDER BY 3, 4 DESC, 1;

-- All the capital cities in a region organised by largest to smallest.
SELECT b.Name, a.Name, a.Region, b.Population FROM country a INNER JOIN city b ON a.Capital = b.ID ORDER BY 3, 4 DESC, 1;

-- The top N populated capital cities in the world where N is provided by the user.
SELECT b.Name, a.Name, b.Population FROM country a INNER JOIN city b ON a.Capital = b.ID ORDER BY 3 DESC, 1 LIMIT 10;

-- The top N populated capital cities in a continent where N is provided by the user.
SELECT b.Name, a.Name, a.Continent, b.Population FROM country a LEFT JOIN city b ON a.Capital = b.ID WHERE a.Continent = "Asia" ORDER BY 4 DESC, 1 LIMIT 10;

-- The top N populated capital cities in a continent where N is provided by the user.
SELECT b.Name, a.Name, a.Region, b.Population FROM country a LEFT JOIN city b ON a.Capital = b.ID WHERE a.Region = "Melanesia" ORDER BY 4 DESC, 1 LIMIT 10;

--Start
-- name, country, district, population

-- All the  cities in the world organised by largest population to smallest.


-- All the cities in a continent organised by largest population to smallest.


-- All the cities in a region organised by largest to smallest.


-- All the cities in a country organised by largest to smallest.


-- All the cities in a district organised by largest to smallest.


-- The top N populated cities in the world where N is provided by the user.


-- The top N populated cities in the continent where N is provided by the user.


-- The top N populated cities in the region where N is provided by the user.


-- The top N populated cities in the country where N is provided by the user.


-- The top N populated cities in the district where N is provided by the user.







