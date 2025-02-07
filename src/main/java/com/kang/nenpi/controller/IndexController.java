package com.kang.nenpi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    
    @GetMapping("/")
    public String index(HttpServletRequest request, Model model){
        // String clientIp = request.getRemoteAddr(); 실제 배포할땐 이걸로?

        String clientIp = request.getHeader("X-Forwarded-For");

        if(clientIp == null){
            clientIp = request.getRemoteAddr();
        }
        // System.out.println(clientIp);
        return "index";
    }
}
