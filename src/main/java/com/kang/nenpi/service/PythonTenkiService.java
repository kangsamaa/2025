package com.kang.nenpi.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PythonTenkiService {
   
    @Autowired
    private RestTemplate restTemplate;

    @Value("${openweather.api.key}")
    private String openWeatherMapApiKey;

    private static final Map<String, String> WEATHER_TRANSLATIONS = Map.ofEntries(
        Map.entry("Clear", "晴れ"),
        Map.entry("Clouds", "曇り"),
        Map.entry("Rain", "雨"),
        Map.entry("Snow", "雪"),
        Map.entry("Drizzle", "時々雨"),
        Map.entry("Thunderstorm", "雷雨"),
        Map.entry("Mist", "霧"),
        Map.entry("Fog", "霧"),
        Map.entry("Haze", "霧"),
        Map.entry("Dust", "黄沙"),
        Map.entry("Smoke", "雲")
    );


    public String getWeather(String city) {
        String openWeatherMapUrl = String.format("http://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric", city, openWeatherMapApiKey);
        String response = restTemplate.getForObject(openWeatherMapUrl, String.class);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(response);
            JsonNode main = root.get("main");
            JsonNode weather = root.get("weather").get(0);
            // JsonNode testkumo = weather.get("weather.main").get(0);
            
            String temperature = main.get("temp").asText();
            // String description = weather.get("description").asText();
            String desc2 = weather.get("main").asText();

            System.out.println(weather + " " + desc2); //tenki test code

            //일본어로 번역하기
            String weatherJP = WEATHER_TRANSLATIONS.getOrDefault(desc2, "error");

            //weatherinfo의 json에서 날씨의 값을 가지고 와서 변환을 해주면될듯

            return String.format("気温: %s°C, 天気: %s", temperature, weatherJP);
        } catch (Exception e) {
            e.printStackTrace();
            return "Unable to fetch weather data";
        }
    }
    
}
