package com.example.employee_management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private UtilityService utilityService;
    @Autowired
    private PasswordEncoder passwordEncoder;
}
