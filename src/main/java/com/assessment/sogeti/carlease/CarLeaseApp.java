package com.assessment.sogeti.carlease;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class CarLeaseApp {
    
	public static void main(String[] args) {
        SpringApplication.run(CarLeaseApp.class, args);
    }
    
}
