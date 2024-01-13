// WeatherData.java

package com.weatherApi.weather;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "weatherData")
@Data
public class WeatherData {

    @Id
    private String id;
    private String cityName;
    //private String weatherResponse;

    // Additional fields
    private double temp;
    private double feelsLike;
    private int pressure;
    private int humidity;
    private double lon;
    private double lat;

    // Getter and Setter methods for all fields, including weatherResponse
}
