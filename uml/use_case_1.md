# Use Case 1: Output a Country Report 

## CHARACTERISTIC INFORMATION

### Goal in Context

As a ***business analyst***, I want to produce a *Country Report* that is organised by various parameters, including population, region, and name, for analysis so that I can provide further analysis as required by my organisation.

### Scope

All countries in database.

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

Business Analyst

### Trigger

A request is sent to the Business Analyst from a user requesting a data product.

## MAIN SUCCESS SCENARIO

1. Business Analyst correctly identifies variables to execute the query.
2. Business Analyst is competent on the system and successfully submits a request to the application to process.
3. The application produces an accurate report that the Business Analyst can analyse. 
4. Business Analyst uses data to advise data product requestor.

## EXTENSIONS

1. **Business Analyst position does not exist:**
    1. HR advises there is no Business Analyst to execute and analyze the report.
   
## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Februrary 23, 2022