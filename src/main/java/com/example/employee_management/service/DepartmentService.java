package com.example.employee_management.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.employee_management.dto.DepartmentDTO;
import com.example.employee_management.model.Department;
import com.example.employee_management.repository.DepartmentRepository;

@Service
public class DepartmentService {

    private DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<DepartmentDTO> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream()
                .map(department -> {
                    DepartmentDTO dto = new DepartmentDTO();
                    dto.setId(department.getId());
                    dto.setName(department.getName());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public DepartmentDTO create(DepartmentDTO departmentDTO) {
        Department department = new Department();
        department.setName(departmentDTO.getName());
        department = departmentRepository.save(department);

        DepartmentDTO dto = new DepartmentDTO();
        dto.setId(department.getId());
        dto.setName(department.getName());
        return dto;
    }

    public DepartmentDTO update(Long id, DepartmentDTO departmentDTO) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found with id " + id));
        department.setName(departmentDTO.getName());
        department = departmentRepository.save(department);

        DepartmentDTO dto = new DepartmentDTO();
        dto.setId(department.getId());
        dto.setName(department.getName());
        return dto;
    }

    public void delete(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found with id " + id));
        departmentRepository.delete(department);
    }
}
