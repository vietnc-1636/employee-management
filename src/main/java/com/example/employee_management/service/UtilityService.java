package com.example.employee_management.service;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.employee_management.dto.EmployeeDTO;
import com.example.employee_management.model.Employee;

@Service
public class UtilityService {

    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    /**
     * Constructs a new {@code UtilityService} with the specified
     * {@link PasswordEncoder} and {@link ModelMapper}.
     *
     * @param passwordEncoder the password encoder to be used for encoding passwords
     * @param modelMapper     the model mapper to be used for mapping between
     *                        objects
     */
    public UtilityService(PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    /**
     * Generates a unique employee code by concatenating the prefix "EMP" with the
     * current system time in milliseconds.
     * This ensures that each generated code is unique based on the timestamp at the
     * moment of generation.
     *
     * @return a unique employee code string in the format "EMP{timestamp}"
     */
    public String autoGenerateEmployeeCode() {
        return "EMP" + System.currentTimeMillis();
    }

    /**
     * Encodes the given raw password using the configured password encoder.
     *
     * @param rawPassword the plain text password to encode
     * @return the encoded (hashed) password
     */
    public String encodePassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    /**
     * Verifies whether the provided raw password matches the encoded password.
     *
     * @param rawPassword     the plain text password to verify
     * @param encodedPassword the previously encoded password to compare against
     * @return {@code true} if the raw password matches the encoded password,
     *         {@code false} otherwise
     */
    public boolean verifyPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public EmployeeDTO convertToEmployeeDTO(Employee employee) {
        // Use modelMapper to map Employee to EmployeeDTO
        EmployeeDTO dto = modelMapper.map(employee, EmployeeDTO.class);

        // Manually set departmentName and departmentId if department is not null
        if (employee.getDepartment() != null) {
            dto.setDepartmentName(employee.getDepartment().getName());
            dto.setDepartmentId(String.valueOf(employee.getDepartment().getId()));
        }
        return dto;
    }
}
