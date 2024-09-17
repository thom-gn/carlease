package com.assessment.sogeti.carlease.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/session")
public class SessionController {

    @GetMapping("/token")
    public ResponseEntity<String> getSessionToken(HttpSession session) {
        String accessToken = (String) session.getAttribute("access_token");
        String expired_at = (String) session.getAttribute("expired_at");
        if (accessToken != null) {
            return ResponseEntity.ok("accesstoken: "+ accessToken + ". expired at: " + expired_at );
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No Token Found");
        }
    }
}

