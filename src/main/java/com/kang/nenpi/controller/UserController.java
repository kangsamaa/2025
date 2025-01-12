package com.kang.nenpi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kang.nenpi.entity.User;
import com.kang.nenpi.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
        // 사용자 추가
    @PostMapping
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }
    
    @GetMapping("/")
    public String index(){
        return "index";
    }

    // 모든 사용자 목록 조회
    @GetMapping("/users")
    public String getUsers(Model model){
        List<User> users = userService.getAllusers();
        System.out.println("users in controller " + users);
        model.addAttribute("users", users);
        return "user_list";
    }

}