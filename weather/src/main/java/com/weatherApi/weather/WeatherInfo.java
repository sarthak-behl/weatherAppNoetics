package com.weatherApi.weather;

import lombok.Data;

@Data
public class WeatherInfo {
    private String description;
    private double temp;
    private double feels_like;
    private int pressure;
    private int humidity;
    private String country;
    private double lon;
    private double lat;

    // Constructor to initialize the fields
    public WeatherInfo(String description, double temp, double feels_like, int pressure, int humidity, String country, double lon, double lat) {
        this.description = description;
        this.temp = temp;
        this.feels_like = feels_like;
        this.pressure = pressure;
        this.humidity = humidity;
        this.country = country;
        this.lon = lon;
        this.lat = lat;
    }

   
}
