package com.example.employee_management.service;

import org.springframework.stereotype.Service;

import com.example.employee_management.dto.EmployeeDTO;
import com.example.employee_management.model.Employee;

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

    /**
     * Converts an Employee entity to an EmployeeDTO.
     *
     * @param employee the Employee entity to convert
     * @return the corresponding EmployeeDTO
     */
    public EmployeeDTO convertToDTO(Employee employee) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setEmail(employee.getEmail());
        dto.setEmployeeCode(autoGenerateEmployeeCode(employee.getId().intValue()));
        dto.setDepartmentName(employee.getDepartment().getName());

        return dto;
    }

}
