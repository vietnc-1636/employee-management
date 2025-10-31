package com.example.employee_management.controller;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class HealthController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, Spring Boot! Your application is running successfully.";
    }

    @GetMapping("/health")
    public String healthCheck() {
        return "Employee Management Service is running.";
    }

    /**
     * Scheduled task to log system status every 30 seconds
     */
    @Scheduled(fixedRate = 30000) // 30000ms = 30 seconds
    public void logSystemStatus() {
        log.info("Employee Management Service is running. System status is healthy.");
    }
}
