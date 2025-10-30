package com.example.employee_management.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDTO {

    private Long id;

    @NotBlank(message = "Employee name cannot be blank")
    @Size(max = 50, message = "Employee name must not exceed 50 characters")
    private String name;

    @NotBlank(message = "Employee email cannot be blank")
    @Email(message = "Employee email should be valid")
    @Size(max = 100, message = "Employee email must not exceed 100 characters")
    private String email;

    private Long employeeCode;

    @NotNull(message = "Department cannot be null")
    private DepartmentDTO department;
}
