# Use Case: Produce a Country Report

## CHARACTERISTIC INFORMATION

### Goal in Context

As an *Administrator*, I want to produce a Country Report organised by various parameters, including country code, name, continent, region, population, and the associated capital for analysis to provide further analysis as required by the World Health Organisation.

### Scope

All countries and capital cities in the database. Several variations of reports will require the need to organise the data from largest to smallest. There is also a requirement to allow a variable to be defined that may restrict what countries are included in each report variation.
### Level

Primary task.

### Preconditions

There are only a finite number of countries globally; therefore, the database will contain a limited number of records for analysis.

It is known that the report will contain the following fields: *Country Code, Name, Continent, Region, Population, Capital*. It is also assumed that the ***[city]*** table includes a foreign key to the capital city for reporting purposes.

### Success End Condition

The application generates a specified report that includes the corresponding records to *Country Code, Country Name, Continent, Region, Population, and Capital City.*

### Failed End Condition

No report is produced, or the parameters do not produce the correct report.

### Primary Actor

Administrator

### Trigger

A request is sent to the Administrator from a user requesting a data product.

## MAIN SUCCESS SCENARIO

1. Administrator correctly identifies variables to execute the query.
2. Administrator is competent on the system and successfully submits a request to the application to process.
3. The application produces an accurate report that the Administrator can analyse.
4. Administrator uses data to advise data product requestor.

## EXTENSIONS

1. **Administrator position does not exist:**
   1. HR advises there is no Administrator to execute and analyze the report.

## SUB-VARIATIONS

None.

## Schedule

**DUE DATE**: February 23, 2022

## References

This documented was based on the work of [Alistair Cockburn](https://cis.bentley.edu/lwaguespack/CS360_Site/Downloads_files/Use%20Case%20Template%20%28Cockburn%29.pdf).
