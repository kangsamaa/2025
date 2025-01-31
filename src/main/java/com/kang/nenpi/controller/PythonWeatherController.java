package com.kang.nenpi.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kang.nenpi.service.PythonGetIpService;
import com.kang.nenpi.service.PythonTenkiService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class PythonWeatherController {

    @Autowired
    private PythonTenkiService pythonTenkiService;

    @Autowired
    private PythonGetIpService pythonGetIpService;

    @GetMapping("/weather")
    public String getWeather(HttpServletRequest request, Model model) {
        // 1. IP 주소로 도시명 추출
        String clientIp = request.getRemoteAddr();
        String testIp = "126.25.132.156";
        String city = pythonGetIpService.getLocationByIp(testIp);
        
        System.out.println(city);
    
        // 2. 도시명을 이용해 날씨 정보 가져오기
        String weatherInfo = pythonTenkiService.getWeather(city);

        //Model Icon Add
        String weatherIcon = "01d";
        

        // 결과를 모델에 추가하여 화면에 전달
        model.addAttribute("weatherInfo", weatherInfo);
        model.addAttribute("city", city);
        model.addAttribute("weatherIcon", weatherIcon);
        model.addAttribute("ip", clientIp); //표시는 0.0.0.0으로 톰캣으로 하고있으니까
        return "weather";  // Thymeleaf 템플릿 (weather.html)
    }
}
