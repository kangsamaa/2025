package com.kang.nenpi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kang.nenpi.entity.User;
import com.kang.nenpi.repository.UserRepository;
import com.kang.nenpi.service.UserService;

@Controller
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        userService.registerUser(user);
        model.addAttribute("message", "회원가입이 완료되었습니다!");
        return "register";
    }
    //     // 사용자 추가
    // @PostMapping
    // public User addUser(@RequestBody User user) {
    //     return userService.addUser(user);
    // }
    

    // 모든 사용자 목록 조회
    @GetMapping("/users")
    public String getUsers(Model model){
        List<User> users = userService.getAllusers();
        // System.out.println("users in controller " + users);
        model.addAttribute("users", users);
        return "user_list";
    }

    //save user form
    @GetMapping("/user/create")
    public String showForm(Model model){
        model.addAttribute("user", new User());
        return "user_form";
    }

    // @PostMapping("/user/create")
    // public String listUsers(@ModelAttribute User user){
    //     userRepository.save(user);
    //     return "redirect:/users";
    // }

}