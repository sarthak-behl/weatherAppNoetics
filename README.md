# Weather API Project

## Overview

Welcome to the Weather API project, a robust and user-friendly application that provides real-time weather information for various cities. This project utilizes the OpenWeatherMap API to fetch weather data, stores it in a MongoDB database, and offers a range of functionalities for users to explore.

## Key Features

### 1. **City Weather Information**

Easily retrieve current weather data for a specific city by making a simple API call:
   - **Endpoint:** `GET /weather?cityName={city}`
   - **Example:** `http://localhost:8080/weather?cityName=Agra`
   - Obtain temperature, feels-like temperature, pressure, humidity, and geographical coordinates.

### 2. **All Cities Weather Data**

Explore weather information for all stored cities with the ability to paginate through the results:
   - **Endpoint:** `GET /weather/all?page={page}&pageSize={pageSize}`
   - **Example:** `http://localhost:8080/weather/all?page=0&pageSize=10`
   - Retrieve paginated data for better organization.

### 3. **Sort Weather Data**

Sort the stored weather information based on different fields like latitude (`lat`), longitude (`lon`), or any other available field:
   - **Endpoint:** `GET /weather/sort?field={fieldName}`
   - **Example:** `http://localhost:8080/weather/sort?field=lat`
   - Get sorted weather data for a specific field in ascending order.

## How to Use

1. **Get City Weather Information:**
   - Use the `/weather` endpoint with the `cityName` parameter to get weather details for a specific city.

2. **Explore All Cities Weather Data:**
   - Utilize the `/weather/all` endpoint to retrieve weather data for all cities with optional pagination parameters (`page` and `pageSize`).

3. **Sort Weather Data:**
   - Sort the stored weather data based on a specific field using the `/weather/sort` endpoint with the `field` parameter.

## Applications

- **Weather Forecasting:** Access up-to-date weather information for any city, aiding in forecasting and planning.
- **Data Analysis:** Explore and analyze weather trends by sorting data based on different parameters.
- **API Integration:** Seamlessly integrate weather data into your applications for a wide range of industries.

## Example Use Cases

- **Travel Planning:** Plan your trips by checking the weather conditions of your destination.
- **Research:** Analyze weather patterns for scientific research or academic purposes.
- **Development:** Integrate weather data into your applications for a personalized user experience.

## Get Started

1. Clone the repository.
2. Set up your MongoDB database and provide the connection details in the application properties.
3. Obtain an API key from OpenWeatherMap and set it in the application properties.
4. Run the application and start making API calls to retrieve weather information.

Explore the Weather API project and unlock a world of possibilities with real-time, organized, and easily accessible weather data.
## Technical Flow of the Weather API Project

### 1. **User Interaction:**
   - Users interact with the Weather API through HTTP requests, leveraging various endpoints to retrieve specific weather information.

### 2. **Controller Layer (WeatherController):**
   - **Endpoint Handling:**
     - Handles incoming HTTP requests and delegates responsibilities to the service layer.
     - Provides endpoints for getting weather by city, retrieving all weather data, and sorting weather data.

### 3. **Service Layer (WeatherService):**
   - **OpenWeatherMap Integration:**
     - Utilizes RestTemplate to make requests to the OpenWeatherMap API based on the provided city name.
     - Parses the JSON response to extract relevant weather information.

   - **Data Storage:**
     - Saves the retrieved weather data to a MongoDB database using the WeatherDataRepository.

   - **Business Logic:**
     - Implements business logic for handling exceptions, such as WeatherNotFoundException.
     - Supports pagination and sorting functionalities.

### 4. **Repository Layer (WeatherDataRepository):**
   - Extends the MongoRepository interface to perform CRUD operations on WeatherData entities in the MongoDB database.

### 5. **Model Layer (WeatherData):**
   - Represents the structure of weather data with fields like temperature, feels-like temperature, pressure, humidity, and geographical coordinates.
   - Annotated with Spring Data MongoDB annotations for integration with MongoDB.

### 6. **Exception Handling (WeatherNotFoundException):**
   - Custom exception class to handle situations where weather data for a city is not found.

### 7. **Application Properties:**
   - Configuration file containing properties such as the OpenWeatherMap API key and MongoDB connection details.

### 8. **Spring Boot Application:**
   - The main application class that bootstraps the Spring Boot application.

### 9. **API Endpoints:**
   - Exposed through the WeatherController, these endpoints cater to different user requirements:
     - `/weather`: Get weather information for a specific city.
     - `/weather/all`: Retrieve all weather data with optional pagination parameters.
     - `/weather/sort`: Sort weather data based on a specified field.

### 10. **External Services:**
   - Communicates with the OpenWeatherMap API to fetch real-time weather data.
   - Utilizes a MongoDB database to store and retrieve weather information for various cities.

### 11. **User Interface (External Applications):**
   - External applications can integrate and consume the Weather API by making HTTP requests to the provided endpoints.

### 12. **Logging and Error Handling:**
   - Incorporates logging mechanisms to record important events.
   - Implements error handling to gracefully manage exceptions and provide meaningful responses to users.

### 13. **Deployment:**
   - The application can be deployed to a server or cloud platform, making the Weather API accessible to a wide range of users.

This technical flow illustrates the seamless integration of components in the Weather API project, providing a reliable and scalable solution for accessing and managing weather data.
