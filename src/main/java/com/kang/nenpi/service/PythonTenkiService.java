package com.kang.nenpi.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kang.nenpi.entity.WeatherResponse;

@Service
public class PythonTenkiService {
   
    @Autowired
    private RestTemplate restTemplate;

    @Value("${openweather.api.key}")
    private String openWeatherMapApiKey;

    public String getWeather(String city) {
        String openWeatherMapUrl = String.format("http://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric", city, openWeatherMapApiKey);
        String response = restTemplate.getForObject(openWeatherMapUrl, String.class);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(response);
            JsonNode main = root.get("main");
            JsonNode weather = root.get("weather").get(0);

            String temperature = main.get("temp").asText();
            String description = weather.get("description").asText();

            return String.format("Temperature: %s°C, Description: %s", temperature, description);
        } catch (Exception e) {
            e.printStackTrace();
            return "Unable to fetch weather data";
        }
    }
    
}
