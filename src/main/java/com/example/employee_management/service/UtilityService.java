package com.example.employee_management.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.employee_management.dto.DepartmentDTO;
import com.example.employee_management.dto.EmployeeDTO;
import com.example.employee_management.model.Department;
import com.example.employee_management.model.Employee;

@Service
public class UtilityService {

    private final ModelMapper modelMapper;

    public UtilityService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

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
     * @param employee the Employee entity to be converted
     * @return EmployeeDTO object mapped from the given Employee entity
     */
    public EmployeeDTO convertToEmployeeDTO(Employee employee) {
        EmployeeDTO dto = modelMapper.map(employee, EmployeeDTO.class);

        return dto;
    }

    /**
     * Converts a Department entity to a DepartmentDTO.
     *
     * @param department the Department entity to be converted
     * @return DepartmentDTO object mapped from the given Department entity
     */
    public DepartmentDTO convertToDepartmentDTO(Department department) {
        DepartmentDTO dto = modelMapper.map(department, DepartmentDTO.class);

        return dto;
    }
}
