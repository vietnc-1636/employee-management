package com.example.employee_management.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.employee_management.dto.EmployeeDTO;
import com.example.employee_management.model.Department;
import com.example.employee_management.model.Employee;
import com.example.employee_management.repository.DepartmentRepository;
import com.example.employee_management.repository.EmployeeRepository;

@Service
public class EmployeeService {

    private UtilityService utilityService;
    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;

    public EmployeeService(UtilityService utilityService, EmployeeRepository employeeRepository,
            DepartmentRepository departmentRepository) {
        this.utilityService = utilityService;
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    public List<EmployeeDTO> getEmployees(String keyword) {
        List<Employee> employees;

        if (keyword != null && !keyword.isEmpty()) {
            employees = employeeRepository.findByKeyword(keyword);

            return employees.stream()
                    .map(utilityService::convertToDTO)
                    .collect(Collectors.toList());
        }

        employees = employeeRepository.findAll();

        return employees.stream()
                .map(utilityService::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public EmployeeDTO create(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setEmail(employeeDTO.getEmail());

        Department department = departmentRepository.findById(employeeDTO.getDepartmentId())
                .orElseThrow(() -> new RuntimeException(
                        "Department not found with id " + employeeDTO.getDepartmentId()));
        employee.setDepartment(department);
        employee = employeeRepository.save(employee);

        return utilityService.convertToDTO(employee);
    }

    @Transactional
    public EmployeeDTO update(Long id, EmployeeDTO employeeDTO) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id " + id));
        employee.setName(employeeDTO.getName());
        employee.setEmail(employeeDTO.getEmail());

        Department department = departmentRepository.findById(employeeDTO.getDepartmentId())
                .orElseThrow(() -> new RuntimeException(
                        "Department not found with id " + employeeDTO.getDepartmentId()));
        employee.setDepartment(department);
        employee = employeeRepository.save(employee);

        return utilityService.convertToDTO(employee);
    }

    public void delete(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id " + id));
        employeeRepository.delete(employee);
    }
}
