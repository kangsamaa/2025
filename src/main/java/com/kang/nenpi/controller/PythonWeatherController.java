package com.kang.nenpi.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kang.nenpi.entity.IpLocationResponse;
import com.kang.nenpi.entity.WeatherResponse;
import com.kang.nenpi.enums.JapanPrefecture;
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
        String testIp = "126.25.132.156"; //현재는 테스트 ip로만 구현 서비스할때에 ip를 가져와서 하자 clienIp로
        IpLocationResponse location = pythonGetIpService.getLocationByIp(testIp);

        String region = location.getCity();
        String city = location.getRegionName();
        String cityKanji = JapanPrefecture.getKanjiByEnglish(city);
        // System.out.println(city + " " + region);

        List<WeatherResponse> forecastWeather = pythonTenkiService.getWeather(city);

        // // 2. 도시명을 이용해 날씨 정보 가져오기
        // String weatherInfo = pythonTenkiService.getWeather(city);

        // // 3. 지명을 이용해 날씨정보 가져오기
        // String weatherRegion = pythonTenkiService.getWeather(region);

        // 결과를 모델에 추가하여 화면에 전달
        model.addAttribute("forecastWeather", forecastWeather);
        model.addAttribute("city", cityKanji); //kanji로 표시하기
        model.addAttribute("ip", clientIp); //표시는 0.0.0.0으로 톰캣으로 하고있으니까
        model.addAttribute("region", region);
        return "weather";  // Thymeleaf 템플릿 (weather.html)
    }
}
