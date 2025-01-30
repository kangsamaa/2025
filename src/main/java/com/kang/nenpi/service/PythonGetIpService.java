package com.kang.nenpi.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PythonGetIpService {
   
    @Value("${ipstack.api.key}")
    private String ipstackApikey;

    @Autowired
    private RestTemplate restTemplate;

    public PythonGetIpService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public String getLocationByIp(String ip) {
        String ipstackUrl = String.format("http://api.ipstack.com/%s?access_key=%s", ip, ipstackApikey);
        String response = restTemplate.getForObject(ipstackUrl, String.class);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(response);
            return root.get("region_name").asText(); // 도시 정보만 추출
        } catch (Exception e) {
            e.printStackTrace();
            return "Tokyo"; // 기본값
        }
    }

}
