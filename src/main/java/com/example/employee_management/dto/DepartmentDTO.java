package com.example.employee_management.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class DepartmentDTO {
    private Long id;

    @NotNull(message = "Department name cannot be null")
    @NotBlank(message = "Department name cannot be blank")
    @Size(min = 2, max = 100, message = "Department name must be between 2 and 100 characters")
    private String name;

    // Constructors
    public DepartmentDTO() {
    }

    public DepartmentDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
