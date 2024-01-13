package com.weatherApi.weather;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface WeatherDataRepository extends MongoRepository<WeatherData,String> {
}
