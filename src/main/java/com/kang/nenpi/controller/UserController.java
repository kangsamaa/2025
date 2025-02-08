package com.kang.nenpi.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kang.nenpi.entity.User;
import com.kang.nenpi.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;

    // 로그인 처리
    @PostMapping("/login")
    @ResponseBody
    public Map<String, Object> login(@RequestParam String username, @RequestParam String password, HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        User user = userService.findByUsername(username);
        if (user != null && userService.checkPassword(user, password)) {
            session.setAttribute("loggedInUser", user.getUsername());
            response.put("status", "success");
        } else {
            response.put("status", "error");
            response.put("message", "ログインに失敗しました。ユーザー名またはパスワードが正しくありません。");
        }
        return response;
    }

    // 로그아웃 처리
    @PostMapping("/logout")
    @ResponseBody
    public void logout(HttpSession session) {
        session.invalidate();
    }

    // 세션 확인
    @GetMapping("/session")
    @ResponseBody
    public Map<String, Object> checkSession(HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        String loggedInUser = (String) session.getAttribute("loggedInUser");
        response.put("loggedIn", loggedInUser != null);
        response.put("username", loggedInUser);
        return response;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        userService.registerUser(user);
        model.addAttribute("message", "회원가입이 완료되었습니다!");
        return "register";
    }

    // 모든 사용자 목록 조회
    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getAllusers());
        return "user_list";
    }

    // 사용자 생성 폼
    @GetMapping("/user/create")
    public String showForm(Model model) {
        model.addAttribute("user", new User());
        return "user_form";
    }
}
