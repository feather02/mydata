package com.example.web_security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewsController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
    @GetMapping("/home")
    public String home() {
        return "home";
    }
    @GetMapping("/")
    public String index() {
        return "hello";
    }
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("new","login");
        return "login";
    }
}