// WeatherService.java

package com.weatherApi.weather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class WeatherService {

    @Autowired
    private WeatherDataRepository weatherDataRepository;

    @Value("${openweathermap.apiKey}")
    private String apiKey;

    public WeatherData getWeather(String cityName) throws JsonProcessingException, WeatherNotFoundException {
        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&appid=" + apiKey;

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(apiUrl, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(result);

        if (jsonNode.has("cod") && jsonNode.get("cod").asInt() == 404) {
            // Handle city not found
            throw new WeatherNotFoundException("Weather information not found for city: " + cityName);
        }

        WeatherData weatherData = new WeatherData();
        weatherData.setCityName(cityName);
        weatherData.setTemp(jsonNode.path("main").path("temp").asDouble());
        weatherData.setFeelsLike(jsonNode.path("main").path("feels_like").asDouble());
        weatherData.setPressure(jsonNode.path("main").path("pressure").asInt());
        weatherData.setHumidity(jsonNode.path("main").path("humidity").asInt());
        weatherData.setLon(jsonNode.path("coord").path("lon").asDouble());
        weatherData.setLat(jsonNode.path("coord").path("lat").asDouble());


        weatherDataRepository.save(weatherData);

        return weatherData;
    }



    public Page<WeatherData> getAllWeather(int page, int pageSize) throws JsonProcessingException, WeatherNotFoundException {
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        return weatherDataRepository.findAll(pageRequest);
    }




    public List<WeatherData> sortBasedUponSomeField(String field) throws WeatherNotFoundException {
        List<WeatherData> sortedWeatherData = weatherDataRepository.findAll(Sort.by(Sort.Direction.ASC, field));

        if (sortedWeatherData.isEmpty()) {
            throw new WeatherNotFoundException("Weather information not found for any city.");
        }

        return sortedWeatherData;
    }





}
