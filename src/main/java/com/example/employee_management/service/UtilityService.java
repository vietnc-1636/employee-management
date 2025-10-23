package com.example.employee_management.service;

import org.springframework.stereotype.Service;

@Service
public class UtilityService {

    /**
     * Generates a unique employee code by concatenating the prefix "EMP" with the
     * current system time in milliseconds.
     * This ensures that each generated code is unique based on the timestamp.
     *
     * @return a unique employee code string in the format "EMP_{timestamp}"
     */
    public String autoGenerateEmployeeCode() {
        // Logic to generate a unique employee code
        return "EMP_" + System.currentTimeMillis();
    }
}
