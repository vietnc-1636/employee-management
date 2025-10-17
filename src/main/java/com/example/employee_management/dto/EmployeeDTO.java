package com.example.employee_management.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class EmployeeDTO {
    private String id;

    @NotNull(message = "Employee name cannot be null")
    @NotBlank(message = "Employee name cannot be blank")
    @Size(min = 2, max = 20, message = "Employee name must be between 2 and 20 characters")
    private String name;

    @NotNull(message = "Employee code cannot be null")
    @NotBlank(message = "Employee code cannot be blank")
    @Size(min = 2, max = 10, message = "Employee code must be between 2 and 10 characters")
    private String employeeCode;

    @NotNull(message = "Email cannot be null")
    @NotBlank(message = "Email cannot be blank")
    @Size(max = 50, message = "Email must be less than 50 characters")
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "Role cannot be null")
    @NotBlank(message = "Role cannot be blank")
    private String role;

    @NotNull(message = "Department ID cannot be null")
    @NotBlank(message = "Department ID cannot be blank")
    private String departmentId;
    private String departmentName;

    // Constructors
    public EmployeeDTO() {
    }

    public EmployeeDTO(String id, String name, String employeeCode, String email, String role, String departmentId,
            String departmentName) {
        this.id = id;
        this.name = name;
        this.employeeCode = employeeCode;
        this.email = email;
        this.role = role;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
