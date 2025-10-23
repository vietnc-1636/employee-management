package com.example.employee_management.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.employee_management.dto.EmployeeDTO;

@Service
public class EmployeeService {

    @Autowired
    private UtilityService utilityService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeDTO> employees = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            EmployeeDTO employee = new EmployeeDTO();
            employee.setId(utilityService.autoGenerateEmployeeCode(i));
            employee.setName("Employee " + i);
            employee.setEmail("employee" + i + "@example.com");
            employee.setDepartmentId((long) (i % 2));
            employees.add(employee);
        }

        return employees;
    }

    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        // Code to save the employee to the database (omitted for brevity)

        return employeeDTO;
    }
}
