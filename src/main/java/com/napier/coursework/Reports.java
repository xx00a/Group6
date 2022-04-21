package com.napier.coursework;

public enum Reports {

    REPORT_ALL_COUNTRIES_BY_POPULATION_DESC(1, "Country Report","All the countries in the world organised by largest population to smallest",
            """
                SELECT country.Code,country.Name,country.Continent,country.Region,
                       country.Population,
                       c.name AS 'Capital'
                FROM   country
                       LEFT JOIN city c
                              ON country.capital = c.id
                ORDER  BY country.population DESC;
                """, ReportTypes.REPORT_COUNTRY),
    REPORT_ALL_COUNTRIES_IN_CONTINENT(2, "Country Report","All the countries in a continent organised by largest population to smallest",
            """
             SELECT country.Code,country.Name,country.Continent,country.Region,
                    country.Population,
                    c.name AS 'Capital'
             FROM   country
                    LEFT JOIN city c
                           ON country.capital = c.id
             WHERE  country.continent LIKE 'XXvarArgXX'
             ORDER  BY country.population DESC;
             """, ReportTypes.REPORT_COUNTRY),
    REPORT_ALL_COUNTRIES_IN_REGION(3, "Country Report", "All the countries in a region organised by largest population to smallest",
            """
                SELECT country.Code,country.Name,country.Continent,country.Region,
                       country.Population,
                       c.name AS 'Capital'
                FROM   country
                       LEFT JOIN city c
                              ON country.capital = c.id
                WHERE  country.region LIKE 'XXvarArgXX'
                ORDER  BY country.population DESC;
                """, ReportTypes.REPORT_COUNTRY),

    REPORT_TOP_POPULATED_COUNTRIES_IN_WORLD(4, "Country Report", "The top YYvarLimitYY populated countries in the world",
            """
                SELECT country.Code,country.Name,country.Continent,country.Region,
                       country.Population,
                       c.name AS 'Capital'
                FROM   country
                       LEFT JOIN city c
                              ON country.capital = c.id
                ORDER  BY country.population DESC
                LIMIT  0, YYvarLimitYY;
                """, ReportTypes.REPORT_COUNTRY),

    REPORT_TOP_POPULATED_COUNTRIES_IN_CONTINENT(5, "Country Report","The top YYvarLimitYY populated countries in XXvarArgXX",
                  """
                SELECT country.Code,country.Name,country.Continent,country.Region,
                       country.Population,
                       c.name AS 'Capital'
                FROM   country
                       LEFT JOIN city c
                              ON country.capital = c.id
                WHERE  country.continent LIKE 'XXvarArgXX'
                ORDER  BY country.population DESC
                LIMIT  0, YYvarLimitYY;
                """, ReportTypes.REPORT_COUNTRY),

    REPORT_TOP_POPULATED_COUNTRIES_IN_REGION(6, "Country Report","The top YYvarLimitYY populated countries in XXvarArgXX",
            """
                SELECT country.Code,country.Name,country.Continent,country.Region,
                       country.Population,
                       c.name AS 'Capital'
                FROM   country
                       LEFT JOIN city c
                              ON country.capital = c.id
                WHERE  country.Region LIKE 'XXvarArgXX'
                ORDER  BY country.population DESC
                LIMIT  0, YYvarLimitYY;
                """,
            ReportTypes.REPORT_COUNTRY),

    REPORT_ALL_CITIES_IN_WORLD(7, "City Report","All the cities in the world organised by largest population to smallest.",
            """
                SELECT city.Name AS Name, country.Name AS Country, city.District, city.Population
                FROM city
                         INNER JOIN country ON city.CountryCode = country.Code
                ORDER BY 4 DESC;
                """,
            ReportTypes.REPORT_CITY),

    REPORT_ALL_CITIES_IN_CONTINENT(8, "City Report","All the cities in XXvarArgXX organised by largest population to smallest.",
            """
                SELECT city.Name AS Name, country.Name AS Country, city.District, city.Population
                FROM city
                INNER JOIN country  ON city.CountryCode = country.Code
                WHERE country.Continent = 'XXvarArgXX'
                ORDER BY 4 DESC, 1
                LIMIT 0, YYvarLimitYY;
               """,
            ReportTypes.REPORT_CITY),

    REPORT_ALL_CITIES_IN_REGION(9,"City Report","All the cities in XXvarArgXX organised by largest population to smallest.",
            """
                SELECT city.Name AS Name, country.Name AS Country, city.District, city.Population
                FROM city
                         INNER JOIN country ON city.CountryCode = country.Code
                WHERE country.Region = 'XXvarArgXX'
                ORDER BY 4 DESC, 1
                LIMIT 0, YYvarLimitYY;
               """,
            ReportTypes.REPORT_CITY),

    REPORT_ALL_CITIES_IN_COUNTRY(10, "City Report","All the cities in XXvarArgXX organised by largest population to smallest.",
            """
                SELECT city.Name AS Name, country.Name AS Country, city.District, city.Population
                FROM city
                         INNER JOIN country ON city.CountryCode = country.Code
                WHERE country.Name = 'XXvarArgXX'
                ORDER BY 4 DESC, 1
                 LIMIT 0, YYvarLimitYY;
               """,
            ReportTypes.REPORT_CITY),

    REPORT_ALL_CITIES_IN_DISTRICT(11, "City Report","All the cities in XXvarArgXX organised by largest population to smallest.",
            """
                SELECT city.Name AS Name, country.Name AS Country, city.District, city.Population
                FROM city
                         INNER JOIN country ON city.CountryCode = country.Code
                WHERE city.District = 'XXvarArgXX'
                ORDER BY 4 DESC;
               """,
            ReportTypes.REPORT_CITY),

    REPORT_TOP_POPULATED_CITIES_IN_WORLD(12, "City Report","The top YYvarLimitYY populated cities in the world",
            """
                        SELECT city.Name AS Name, country.Name AS Country, city.District, city.Population
                        FROM city
                                 INNER JOIN country ON city.CountryCode = country.Code
                        ORDER BY 4 DESC
                        LIMIT 0, YYvarLimitYY;
                       """,
    ReportTypes.REPORT_CITY),

    REPORT_TOP_POPULATED_CITIES_IN_CONTINENT(13, "City Report","The top YYvarLimitYY populated cities in XXvarArgXX",
            """
                        SELECT city.Name AS Name, country.Name AS Country, city.District, city.Population
                        FROM city
                                 INNER JOIN country ON city.CountryCode = country.Code
                        WHERE country.Continent = 'XXvarArgXX'
                        ORDER BY 4 DESC
                        LIMIT 0, YYvarLimitYY;
                        """,
    ReportTypes.REPORT_CITY),

    REPORT_TOP_POPULATED_CITIES_IN_REGION(14, "City Report","The top YYvarLimitYY populated cities in XXvarArgXX",
            """
                SELECT city.Name AS Name, country.Name AS Country, city.District, city.Population
                FROM city
                         INNER JOIN country ON city.CountryCode = country.Code
                WHERE country.Region = 'XXvarArgXX'
                ORDER BY 4 DESC
                LIMIT 0, YYvarLimitYY;
                """,
            ReportTypes.REPORT_CITY),
    REPORT_TOP_POPULATED_CITIES_IN_COUNTRY(15, "City Report","The top YYvarLimitYY populated cities in XXvarArgXX",
            """
                SELECT city.Name AS Name, country.Name AS Country, city.District, city.Population
                FROM city
                         INNER JOIN country ON city.CountryCode = country.Code
                WHERE country.Name = 'XXvarArgXX'
                ORDER BY 4 DESC
                LIMIT 0, YYvarLimitYY;
                """,
            ReportTypes.REPORT_CITY),
    REPORT_TOP_POPULATED_CITIES_IN_DISTRICT(16, "City Report","The top YYvarLimitYY populated cities in XXvarArgXX",
            """
                SELECT city.Name AS Name, country.Name AS Country, city.District, city.Population
                FROM city
                         INNER JOIN country ON city.CountryCode = country.Code
                WHERE city.District = 'XXvarArgXX'
                LIMIT YYvarLimitYY;
                """,
            ReportTypes.REPORT_CITY ),

    REPORT_CAPITAL_CITIES_IN_WORLD(17, "Capital City Report", "All the capital cities in the world organised by largest population to smallest.",
            """
                SELECT b.Name as 'Name', a.Name as 'Country', b.Population as 'Population'
                FROM country a
                         INNER JOIN city b ON a.Capital = b.ID
                ORDER BY 3 DESC, 1;
                """,
            ReportTypes.REPORT_CAPITAL_CITY),

    REPORT_CAPITAL_CITIES_IN_CONTINENT(18, "Capital City Report","All the capital cities in XXvarArgXX organised by largest population to smallest.",
            """
                SELECT b.Name       as 'Name',
                       a.Name       as 'Country',
                       a.Region     as 'Region',
                       b.Population as 'Population'
                FROM country a
                         INNER JOIN city b ON a.Capital = b.ID
                WHERE a.Continent = 'XXvarArgXX'
                ORDER BY 3, 4 DESC, 1;
                """,
            ReportTypes.REPORT_CAPITAL_CITY),

    REPORT_CAPITAL_CITIES_IN_REGION(19, "Capital City Report","All the capital cities in XXvarArgXX organised by largest to smallest.",
            """
            SELECT b.Name as 'Name', a.Name as 'Country', a.Region as 'Region',
                   b.Population as 'Population'
            FROM   country a INNER JOIN city b ON a.Capital = b.ID
            WHERE a.Region = 'XXvarArgXX'
            ORDER  BY 3, 4 DESC, 1;
            """,
            ReportTypes.REPORT_CAPITAL_CITY),

    REPORT_TOP_POPULATED_CAPITAL_CITIES_IN_WORLD(20, "Capital City Report","The top YYvarLimitYY populated capital cities in the world",
            """
                SELECT b.Name as 'Name', a.Name as 'Country', b.Population as 'Population'
                FROM country a
                         INNER JOIN city b ON a.Capital = b.ID
                ORDER BY 3 DESC, 1
                LIMIT 0, YYvarLimitYY;
                """,
            ReportTypes.REPORT_CAPITAL_CITY),
    REPORT_TOP_POPULATED_CAPITAL_CITIES_IN_CONTINENT(21, "Capital City Report","The top YYvarLimitYY populated capital cities in XXvarArgXX",
            """
                        SELECT b.Name       as 'Name',
                               a.Name       as 'Country',
                               a.Continent  as 'Continent',
                               b.Population as 'Population'
                        FROM country a
                                 INNER JOIN city b ON a.Capital = b.ID
                        WHERE a.Continent = 'XXvarArgXX'
                        ORDER BY 4 DESC, 1
                        LIMIT 0, YYvarLimitYY;
                        """,
    ReportTypes.REPORT_CAPITAL_CITY),

    REPORT_TOP_POPULATED_CAPITAL_CITIES_IN_REGION(22, "Capital City Report","The top YYvarLimitYY populated capital cities in XXvarArgXX",
            """
                        SELECT b.Name       as 'Name',
                               a.Name       as 'Country',
                               a.Region     as 'Region',
                               b.Population as 'Population'
                        FROM country a
                                 INNER JOIN city b ON a.Capital = b.ID
                        WHERE a.Region = 'XXvarArgXX'
                        ORDER BY 4 DESC, 1
                        LIMIT 0, YYvarLimitYY;
                        """,
    ReportTypes.REPORT_CAPITAL_CITY),

    REPORT_POPULATION_CITIES_CONTINENT(23, "Population Report","The population of people, people living in cities, and people not living in cities in each continent.",
            """
                SELECT country.continent AS 'Name',
                       (SUM(DISTINCT (country.population)))                        AS 'Total Population',
                       (SUM(DISTINCT (country.population)) - SUM(city.population)) AS 'NOT in cities',
                       ((SUM(DISTINCT (country.population)) - SUM(city.population))) / (SUM(DISTINCT (country.population))) *
                       100                                                         AS 'NOT in cities(%)',
                       SUM(city.population)                                        AS 'IN cities',
                       (((SUM(DISTINCT (city.population))) / (SUM(DISTINCT (country.population))) *
                         100))                                                     AS 'IN cities(%)'
                FROM country
                         JOIN city ON city.countrycode = country.code
                GROUP by country.continent;
                """,
            ReportTypes.REPORT_POPULATION),

    REPORT_POPULATION_CITIES_REGION(24, "Population Report","The population of people, people living in cities, and people not living in cities in each region.",
            """
                SELECT country.region AS 'Name',
                       (SUM(DISTINCT (country.population)))                        AS 'Total Population',
                       (SUM(DISTINCT (country.population)) - SUM(city.population)) AS 'NOT in cities',
                       ((SUM(DISTINCT (country.population)) - SUM(city.population))) / (SUM(DISTINCT (country.population))) *
                       100                                                         AS 'NOT in cities(%)',
                       SUM(city.population)                                        AS 'IN cities',
                       (((SUM(DISTINCT (city.population))) / (SUM(DISTINCT (country.population))) *
                         100))                                                     AS 'IN cities(%)'
                FROM country
                         JOIN city ON city.countrycode = country.code
                GROUP by country.region;
                """,
            ReportTypes.REPORT_POPULATION),

    REPORT_POPULATION_CITIES_COUNTRY(25, "Population Report","The population of people, people living in cities, and people not living in cities in each country.",
            """
                SELECT country.name AS 'Name',
                       (SUM(DISTINCT (country.population)))                        AS 'Total Population',
                       (SUM(DISTINCT (country.population)) - SUM(city.population)) AS 'NOT in cities',
                       ((SUM(DISTINCT (country.population)) - SUM(city.population))) / (SUM(DISTINCT (country.population))) *
                       100                                                         AS 'NOT in cities(%)',
                       SUM(city.population)                                        AS 'IN cities',
                       (((SUM(DISTINCT (city.population))) / (SUM(DISTINCT (country.population))) *
                         100))                                                     AS 'IN cities(%)'
                FROM country
                         JOIN city ON city.countrycode = country.code
                GROUP by country.name;
                """,
            ReportTypes.REPORT_POPULATION),

    REPORT_SHORT_POPULATION_WORLD(26, "Population Report","The population of the world.",
            """
                SELECT 'Name', (SUM(DISTINCT (country.population))) AS 'Total Population'
                FROM country
                         JOIN city ON city.countrycode = country.code;
                """,
            ReportTypes.REPORT_POPULATION_SHORT),

    REPORT_SHORT_POPULATION_CONTINENT(27, "Population Report","The population of XXvarArgXX",
            """
                SELECT country.continent AS 'Name', (SUM(DISTINCT (country.population))) AS 'Total Population'
                FROM country
                         JOIN city ON city.countrycode = country.code
                WHERE country.continent = 'XXvarArgXX';
                """,
            ReportTypes.REPORT_POPULATION_SHORT),


    REPORT_SHORT_POPULATION_REGION(28, "Population Report","The population of XXvarArgXX",
            """
                            SELECT country.region AS 'Name', (SUM(DISTINCT (country.population))) AS 'Total Population'
                            FROM country
                                     JOIN city ON city.countrycode = country.code
                            WHERE country.region = 'XXvarArgXX';
                            """,
            ReportTypes.REPORT_POPULATION_SHORT),
    REPORT_SHORT_POPULATION_(29, "Population Report","The population of XXvarArgXX",
            """
                            SELECT country.Name AS 'Name', (SUM(DISTINCT (country.population))) AS 'Total Population'
                            FROM country
                                     JOIN city ON city.countrycode = country.code
                            WHERE country.Name = 'XXvarArgXX';
                            """,
            ReportTypes.REPORT_POPULATION_SHORT),

    REPORT_SHORT_POPULATION_DISTRICT(30, "Population Report","The population of XXvarArgXX",
            """
                            SELECT city.District AS 'Name', (SUM(DISTINCT (country.population))) AS 'Total Population'
                            FROM country
                                     JOIN city ON city.countrycode = country.code
                            WHERE city.District = 'XXvarArgXX';
                            """,
            ReportTypes.REPORT_POPULATION_SHORT),

    REPORT_SHORT_POPULATION_COUNTRY(31, "Population Report","The population of XXvarArgXX",
            """
                            SELECT city.Name AS 'Name', (SUM(DISTINCT (country.population))) AS 'Total Population'
                            FROM country
                                     JOIN city ON city.countrycode = country.code
                            WHERE city.Name = 'XXvarArgXX';
                            """,
            ReportTypes.REPORT_POPULATION_SHORT),


    REPORT_LANGUAGES_ALL_WORLD(32, "Languages Report","The number of people who speak the following languages (Chinese, English, Hindi, Spanish, Arabic) from greatest number to smallest, including the percentage of the world population",
                    """
                            WITH data as (SELECT countrylanguage.Language AS Language, 
                            Round(Sum(countrylanguage.Percentage/100*country.Population), 0) as Speakers FROM countrylanguage 
                            INNER JOIN country ON countrylanguage.CountryCode = country.Code
                            WHERE Language IN ('Chinese','English','Hindi','Spanish','Arabic') and Percentage > 0 
                            GROUP BY countrylanguage.Language ORDER BY Speakers DESC) SELECT *, 
                            (Speakers/(SELECT Sum(country.Population) from country))*100 as Percentage
                            FROM data GROUP BY Language;
                                            """,
            ReportTypes.REPORT_LANGUAGES);

    final Integer id;
    final String reportHeader;
    final String reportName;
    final String query;
    final ReportTypes reportType;
    Reports(Integer id, String reportHeader,  String reportName, String query, ReportTypes reportType){
        this.id =id;
        this.reportHeader = reportHeader;
        this.reportName = reportName;
        this.query = query;
        this.reportType = reportType;
    }

    public Integer getId() {
        return id;
    }

    public String getQuery() {
        return query;
    }

    public String getReportName() {
        return reportName;
    }

    public String getReportHeader() {return reportHeader;}

    public ReportTypes getReportType() {
        return reportType;
    }
}
