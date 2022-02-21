# Use Case: Produce Capital City Report

## CHARACTERISTIC INFORMATION

### Goal in Context

As an *Administrator*, I want to produce a *Capital City Report* that is determined by largest to smallest population grouped by country, region or as a whole in the world as required by the World Health Organisation.

### Scope

The report will detail population data per capital city and can be grouped by region, continent or as a whole in the world.

### Level

Primary tasks.

### Preconditions

World database is running on a server that will hold data used for statistical population gathering.

### Success End Condition

A capital city population report will be produced according to user input if groupings (as stated above) is needed.

### Failed End Condition

If there are no data available, a message saying so should appear. If any error, show the error message.

### Primary Actor

Any permitted user of the system within the organisation wishing to generate the report.

### Trigger

A request from the organisation to require capital city population data (with groupings or not).

## Main Success Scenario

1. User logs in the system
2. User navigates to the capital city population report generation page
3. User choice any groupings (continent, region, top 10, etc.)
4. User hits the "Generate report" button

## Extensions

1. **Statistician position does not exist within the organisation**
   1.1 HR advises there is no Statisticians to execute and analyze the report.

## Sub-variations

None.

## Schedule

**DUE DATE**: February 23, 2022

##References

This documented was based on the work of [Alistair Cockburn](https://cis.bentley.edu/lwaguespack/CS360_Site/Downloads_files/Use%20Case%20Template%20%28Cockburn%29.pdf).
