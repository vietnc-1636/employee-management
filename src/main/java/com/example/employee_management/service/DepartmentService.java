package com.example.employee_management.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.employee_management.dto.DepartmentDTO;
import com.example.employee_management.exception.ModelNotFoundException;
import com.example.employee_management.model.Department;
import com.example.employee_management.repository.DepartmentRepository;

@Service
public class DepartmentService {

    private final UtilityService utilityService;
    private final DepartmentRepository departmentRepository;
    private final int NOT_FOUND_CODE = 404;

    public DepartmentService(UtilityService utilityService, DepartmentRepository departmentRepository) {
        this.utilityService = utilityService;
        this.departmentRepository = departmentRepository;
    }

    public List<DepartmentDTO> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream()
                .map(utilityService::convertToDepartmentDTO)
                .collect(Collectors.toList());
    }

    public DepartmentDTO create(DepartmentDTO departmentDTO) {
        Department department = new Department();
        department.setName(departmentDTO.getName());
        department = departmentRepository.save(department);

        return utilityService.convertToDepartmentDTO(department);
    }

    public DepartmentDTO update(Long id, DepartmentDTO departmentDTO) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("Department not found with id " + id, NOT_FOUND_CODE));
        department.setName(departmentDTO.getName());
        department = departmentRepository.save(department);

        return utilityService.convertToDepartmentDTO(department);
    }

    public void delete(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("Department not found with id " + id, NOT_FOUND_CODE));
        departmentRepository.delete(department);
    }
}
