package com.example.employee_management.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.employee_management.dto.EmployeeDTO;
import com.example.employee_management.model.Employee;
import com.example.employee_management.repository.DepartmentRepository;
import com.example.employee_management.repository.EmployeeRepository;

@Service
public class EmployeeService {

    private final DepartmentRepository departmentRepository;

    private EmployeeRepository employeeRepository;
    private final UtilityService utilityService;

    public EmployeeService(UtilityService utilityService, EmployeeRepository employeeRepository,
            DepartmentRepository departmentRepository) {
        this.utilityService = utilityService;
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    public List<EmployeeDTO> getAllEmployees(Long departmentId) {
        List<Employee> employees;
        if (departmentId != null) {
            employees = employeeRepository.findByDepartmentId(departmentId);
        } else {
            employees = employeeRepository.findAll();
        }
        return employees.stream().map(utilityService::convertToEmployeeDTO).collect(Collectors.toList());
    }

    public EmployeeDTO getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id " + id));
        return utilityService.convertToEmployeeDTO(employee);
    }

    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setEmployeeCode(utilityService.autoGenerateEmployeeCode()); // Auto-generate employee code
        employee.setEmail(employeeDTO.getEmail());
        employee.setPassword(utilityService.encodePassword("Abc@123456789")); // Default password
        employee.setRole(employeeDTO.getRole() != null ? employeeDTO.getRole() : "User"); // Default role is "User"
        employee.setDepartment(departmentRepository.findById(Long.parseLong(employeeDTO.getDepartmentId()))
                .orElseThrow(() -> new RuntimeException(
                        "Department not found with id " + employeeDTO.getDepartmentId())));

        Employee savedEmployee = employeeRepository.save(employee);
        return utilityService.convertToEmployeeDTO(savedEmployee);
    }

    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id " + id));
        employee.setName(employeeDTO.getName());
        employee.setRole(employeeDTO.getRole() != null ? employeeDTO.getRole() : "User"); // Default role is "User"
        employee.setDepartment(departmentRepository.findById(Long.parseLong(employeeDTO.getDepartmentId()))
                .orElseThrow(() -> new RuntimeException(
                        "Department not found with id " + employeeDTO.getDepartmentId())));

        Employee updatedEmployee = employeeRepository.save(employee);
        return utilityService.convertToEmployeeDTO(updatedEmployee);
    }

    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id " + id));
        employeeRepository.delete(employee);
    }
}
