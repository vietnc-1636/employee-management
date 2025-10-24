package com.example.employee_management.service;

import org.springframework.stereotype.Service;

@Service
public class UtilityService {

    /**
     * Generates a unique employee code based on the provided number.
     * The generated code is calculated by adding 1000 to the input number.
     *
     * @param number the input number to generate the employee code from
     * @return a unique employee code as a Long value
     */
    public Long autoGenerateEmployeeCode(int number) {
        // Logic to generate a unique employee code
        return (Long.valueOf(1000 + number));
    }
}
