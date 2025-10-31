package com.example.employee_management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.employee_management.service.EmployeeService;

@Controller
public class DashboardController {

    private final EmployeeService employeeService;

    public DashboardController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public String showDashboard(Model model) {
        long totalEmployees = employeeService.getTotalEmployeeCount();
        model.addAttribute("totalEmployees", totalEmployees);

        return "dashboard";
    }
}
