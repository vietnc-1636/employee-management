package com.example.employee_management.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, Spring Boot! Your application is running successfully.";
    }

    @GetMapping("/health")
    public String healthCheck() {
        return "Employee Management Service is running.";
    }
}
