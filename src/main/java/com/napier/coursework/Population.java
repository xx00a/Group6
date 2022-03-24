package com.napier.coursework;

/*
 * Comments are needed.
 *
 */

public class Population {

    private String name;
    private Long totalPopulation;
    private Long notInCities;
    private float notInCitiesPercentage;
    private Long inCities;
    private float inCitiesPercentage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTotalPopulation() {
        return totalPopulation;
    }

    public void setTotalPopulation(Long totalPopulation) {
        this.totalPopulation = totalPopulation;
    }

    public Long getNotInCities() {
        return notInCities;
    }

    public void setNotInCities(Long notInCities) {
        this.notInCities = notInCities;
    }

    public float getNotInCitiesPercentage() {
        return notInCitiesPercentage;
    }

    public void setNotInCitiesPercentage(float notInCitiesPercentage) {
        this.notInCitiesPercentage = notInCitiesPercentage;
    }

    public Long getInCities() {
        return inCities;
    }

    public void setInCities(Long inCities) {
        this.inCities = inCities;
    }

    public float getInCitiesPercentage() {
        return inCitiesPercentage;
    }

    public void setInCitiesPercentage(float inCitiesPercentage) {
        this.inCitiesPercentage = inCitiesPercentage;
    }

}
