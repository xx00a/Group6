-- All the capital cities in the world organised by largest population to smallest.
SELECT b.Name as 'Capital City', a.Name as 'Country', b.Population as 'Population' FROM country a INNER JOIN city b ON a.Capital = b.ID ORDER BY 3 DESC, 1;

-- All the capital cities in a continent organised by largest population to smallest.
SELECT b.Name as 'Capital City', a.Name as 'Country', a.Continent as 'Continent', b.Population as 'Population' FROM country a INNER JOIN city b ON a.Capital = b.ID ORDER BY 3, 4 DESC, 1;

-- All the capital cities in a region organised by largest to smallest.
SELECT b.Name as 'Capital City', a.Name as 'Country', a.Region as 'Region', b.Population as 'Population' FROM country a INNER JOIN city b ON a.Capital = b.ID ORDER BY 3, 4 DESC, 1;

-- The top N populated capital cities in the world where N is provided by the user.
SELECT b.Name as 'Capital City', a.Name as 'Country', b.Population as 'Population' FROM country a INNER JOIN city b ON a.Capital = b.ID ORDER BY 3 DESC, 1 LIMIT 10;

-- The top N populated capital cities in a continent where N is provided by the user.
SELECT b.Name as 'Capital City', a.Name as 'Country', a.Continent as 'Continent', b.Population as 'Population' FROM country a INNER JOIN city b ON a.Capital = b.ID WHERE a.Continent = "Asia" ORDER BY 4 DESC, 1 LIMIT 10;

-- The top N populated capital cities in a region where N is provided by the user.
SELECT b.Name as 'Capital City', a.Name as 'Country', a.Region as 'Region', b.Population as 'Population' FROM country a INNER JOIN city b ON a.Capital = b.ID WHERE a.Region = "Melanesia" ORDER BY 4 DESC, 1 LIMIT 10;