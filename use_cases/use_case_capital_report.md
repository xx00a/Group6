# Use Case: Produce Capital City Report

## CHARACTERISTIC INFORMATION

### Goal in Context

As an *Administrator*, I want to produce a *Capital City Report* that is organised by various parameters, including population, region, and name, for analysis so that I can provide further analysis as required by the World Health Organisation

### Scope

The report will entail population data for each capital city in the world.

### Level

Primary task.

### Preconditions

World database is running on a server that will hold data used for statistical population gathering.

It is known that the report will contain the following fields: Name, Country, Region/Continent and Population.

### Success End Condition

A capital city population report will be produced according to user input if groupings (as stated above) is needed.

### Failed End Condition

If there are no data available, a message saying so should appear. If any error, show the error message.

### Primary Actor

**Administrator**

### Trigger

A request from the organisation to require capital city population data (with groupings or not).

## Main Success Scenario

1. **Administrator** logs in the system
2. **Administrator** navigates to the capital city population report generation page
3. **Administrator** choice any groupings (continent, region, top 10, etc.)
4. **Administrator** hits the "Generate report" button

## Extensions

1. **Administrator position does not exist within the organisation:**
   1. HR advises there is no Statisticians to execute and analyze the report.

## Sub-variations

None.

## Schedule

**DUE DATE**: February 23, 2022

## References

This documented was based on the work of [Alistair Cockburn](https://cis.bentley.edu/lwaguespack/CS360_Site/Downloads_files/Use%20Case%20Template%20%28Cockburn%29.pdf).
