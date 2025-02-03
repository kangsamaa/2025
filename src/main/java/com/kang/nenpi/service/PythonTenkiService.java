package com.kang.nenpi.service;

import java.util.ArrayList;
import java.util.List;
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

    public List<String> getWeather(String city) {
        String openWeatherMapUrl = String.format("http://api.openweathermap.org/data/2.5/forecast?q=%s&appid=%s&units=metric&lang=ja", city, openWeatherMapApiKey);
        String response = restTemplate.getForObject(openWeatherMapUrl, String.class);
        
        List<String> weatherList = new ArrayList<>();
        

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(response);

            JsonNode list = root.get("list");

            for(JsonNode node : list){
                String dateTime = node.get("dt_txt").asText();
                String temp = node.get("main").get("temp").asText();
                String description = node.get("weather").get(0).get("description").asText();

                weatherList.add(dateTime + " - " + temp + "°C - " + description);

                if(weatherList.size() >= 24){
                    break;
                }
            }

            // JsonNode main = root.get("main");
            // JsonNode weather = root.get("weather").get(0);
            // // JsonNode testkumo = weather.get("weather.main").get(0);
            
            // String temperature = main.get("temp").asText();
            // // String description = weather.get("description").asText();
            // String desc2 = weather.get("main").asText();

            // //일본어로 번역하기
            // String weatherJP = WEATHER_TRANSLATIONS.getOrDefault(desc2, "error");

            // //weatherinfo의 json에서 날씨의 값을 가지고 와서 변환을 해주면될듯
        } catch (Exception e) {
            e.printStackTrace();
            weatherList.add("fail");
        }
        return weatherList;
    }

}
