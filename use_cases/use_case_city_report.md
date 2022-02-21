# Use Case: Template

## CHARACTERISTIC INFORMATION

### Goal in Context

As an *Administrator*, I want to produce a *City Report* that is organised by various parameters, including name, country, district, population, for analysis so that I can provide further analysis as required by the World Health Organisation.

### Scope

All cities in database.

### Level

Primary task.

### Preconditions

There are only a finite number of cities globally; therefore, the database will contain a limited number of records for analysis.

It is known that the report will contain the following fields: *Name, Country Code, District, Population*. It is also assumed that the ***[city]*** table includes a foreign key to the country for reporting purposes.

### Success End Condition

The application generates a specified report that includes the corresponding records to *Name, Country Code, District, Population*

### Failed End Condition

No report is produced, or the parameters do not produce the correct report.

### Primary Actor

Administrator

### Trigger

A request is sent to the Administrator from a World Health Organisation requesting a report.

## MAIN SUCCESS SCENARIO

1. Administrator correctly identifies variables to execute the query.
2. Administrator is competent on the system and successfully submits a request to the application to process.
3. The application produces an accurate report that the Administrator can analyse.
4.Administrator uses data to produce raport for World Health Organisation.

## EXTENSIONS

1. **Administrator position does not exist:**
   1. HR advises there is no Administrator to execute and analyze the report.

## SUB-VARIATIONS

None.

## Schedule

**DUE DATE**: February 23, 2022

## References

This documented was based on the work of [Alistair Cockburn](https://cis.bentley.edu/lwaguespack/CS360_Site/Downloads_files/Use%20Case%20Template%20%28Cockburn%29.pdf).
