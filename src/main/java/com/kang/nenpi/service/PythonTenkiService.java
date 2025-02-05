package com.kang.nenpi.service;

import java.util.ArrayList;
import java.util.List;

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

    public List<WeatherResponse> getWeather(String city) {
        String url = String.format("http://api.openweathermap.org/data/2.5/forecast?q=%s&appid=%s&units=metric&lang=ja", 
                                   city, openWeatherMapApiKey);
        String response = restTemplate.getForObject(url, String.class);

        List<WeatherResponse> weatherList = new ArrayList<>();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(response);
            JsonNode list = root.get("list");

            for (JsonNode node : list) {
                String dateTime = node.get("dt_txt").asText();
                String temp = node.get("main").get("temp").asText();
                String description = node.get("weather").get(0).get("description").asText();
                String icon = node.get("weather").get(0).get("icon").asText();

                // 날짜와 시간 추출 후, 아침 6시에 해당하는 데이터만 필터링
                String hour = dateTime.split(" ")[1].split(":")[0]; // 시간 추출
                if (hour.equals("06")) {
                    weatherList.add(new WeatherResponse(dateTime, icon, temp + "°C", description));

                    // 3일치 예보만 가져오기 (아침 6시 예보)
                    if (weatherList.size() >= 3) {
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return weatherList;
    }
}
