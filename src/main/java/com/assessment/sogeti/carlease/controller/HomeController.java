package com.assessment.sogeti.carlease.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
    	 String accessToken = (String) session.getAttribute("access_token");
         model.addAttribute("access_token", accessToken);
        return "home"; // The HTML file should be named home.html in src/main/resources/templates
    }
}