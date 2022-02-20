#All the countries in the world organised by largest population to smallest.
select country.Name, country.Population
from country
order by country.Population DESC;

#All the countries in a continent organised by largest population to smallest.
select country.Name, country.Continent, country.Population
from country
order by country.Continent, country.Population DESC;

#All the countries in a region organised by largest population to smallest.
#@x = %% wildcard or define the Region
select country.Region, country.Name, country.Population
from country
where country.Region like '%%'
group by country.Population, country.Region, country.Name
order by country.Population DESC;

#The top N populated countries in the world where N is provided by the user.
#@n = 5, the top number of countries to include
select country.Name, country.Population
from country
order by country.Population DESC
    LIMIT 0,5;

#The top N populated countries in a continent where N is provided by the user.
#@n = 5, the top number of countries to include
select country.Continent, country.Name, country.Population
from country
where country.Continent like 'Asia'
group by country.Continent, country.Name, country.Population
order by country.Population DESC
    LIMIT 0,5;

#The top N populated countries in a region where N is provided by the user.
#@n = 5, the top number of countries to include
select country.Region, country.Name, country.Population
from country
where country.Region like 'Middle East'
group by country.Region, country.Name, country.Population
order by country.Population DESC
    LIMIT 0,5;

#All the cities in a country organised by largest population to smallest.
#@country.name = variable
select c.Name, c.Population
from country
         left join city c on country.Code = c.CountryCode
where country.Name = 'Germany'
order by c.Population DESC;

#The top N populated cities in a country where N is provided by the user.
#@country.name = variable
#@limit = N
select c.Name, c.Population
from country
         left join city c on country.Code = c.CountryCode
where country.Name = 'Germany'
order by c.Population DESC
    limit 0,5;