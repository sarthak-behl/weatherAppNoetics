// WeatherController.java

package com.weatherApi.weather;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping
    public ResponseEntity<WeatherData> getWeather(@RequestParam String cityName) {
        try {
            WeatherData weatherData = weatherService.getWeather(cityName);
            return new ResponseEntity<>(weatherData, HttpStatus.OK);
        } catch (JsonProcessingException e) {
            // Handle JSON processing exception, e.g., log the error
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (WeatherNotFoundException e) {
            // Handle weather not found exception
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @GetMapping("/all")
    public ResponseEntity<Page<WeatherData>> getAllWeather(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        try {
            Page<WeatherData> weatherDataPage = weatherService.getAllWeather(page, pageSize);
            return new ResponseEntity<>(weatherDataPage, HttpStatus.OK);
        } catch (WeatherNotFoundException e) {
            // Handle weather not found exception
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }




    @GetMapping("/sort")
    public ResponseEntity<List<WeatherData>> sortAllFields(@RequestParam(required = false) String field) {
        try {
            List<WeatherData> sortedWeatherData = weatherService.sortBasedUponSomeField(field);
            return new ResponseEntity<>(sortedWeatherData, HttpStatus.OK);
        } catch (WeatherNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }




}
