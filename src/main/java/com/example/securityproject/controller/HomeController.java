package com.example.securityproject.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(Authentication authentication) {
        return authentication == null ? "index" : "redirect:/authenticated";
    }

    @GetMapping("/authenticated")
    public String authenticated() {

        return "authenticated";
    }

    @GetMapping("/login")
    public String login(Authentication authentication) {
        return authentication == null ? "login" : "redirect:/authenticated";
    }

}
