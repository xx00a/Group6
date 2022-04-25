/*
  SET08803 Coursework Application
  ReportTypes Class
  This class contains the enum values for the classes of reports requested, including, table header commands
*/

package com.napier.coursework;

// Dependencies

import java.util.List;

// start enum ReportTypes
public enum ReportTypes {

    REPORT_CAPITAL_CITY("Name", "Country", "Population"),
    REPORT_CITY("Name", "Country", "District", "Population"),
    REPORT_COUNTRY("Code", "Name", "Continent", "Region", "Population", "Capital"),
    REPORT_LANGUAGES("Language", "Speakers", "Percentage"),
    REPORT_POPULATION("Name", "Total Population", "NOT in cities", "NOT in cities(%)", "IN cities", "IN cities(%)"),
    REPORT_POPULATION_SHORT("Name", "Total Population");

    final List<String> headers;
    ReportTypes(String... headers) {
        this.headers = List.of(headers);
    }
    public List<String> getHeaders() {
        return headers;
    }
}
// end ReportTypes
