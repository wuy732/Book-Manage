package com.psx.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    
    // 显示登录页面
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    // 处理登录请求
    @PostMapping("/login")
    public String login(String username, HttpSession session) {
        session.setAttribute("user", username);
        session.setAttribute("role", "ADMIN".equalsIgnoreCase(username) ? "ADMIN" : "USER");
        return "redirect:/book/list";
    }

    // 退出登录
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    // 根路径重定向
    @GetMapping("/")
    public String rootRedirect() {
        return "redirect:/login";
    }
}