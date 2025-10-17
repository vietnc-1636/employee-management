package com.example.employee_management.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employee_management.dto.DepartmentDTO;
import com.example.employee_management.model.Department;
import com.example.employee_management.repository.DepartmentRepository;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public List<DepartmentDTO> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream()
                .map(department -> new DepartmentDTO(department.getId(), department.getName()))
                .collect(Collectors.toList());
    }

    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO) {
        Department department = new Department();
        department.setName(departmentDTO.getName());
        Department createdDepartment = departmentRepository.save(department);
        return new DepartmentDTO(createdDepartment.getId(), createdDepartment.getName());
    }

    public DepartmentDTO updateDepartment(Long id, DepartmentDTO departmentDTO) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found with id " + id));
        department.setName(departmentDTO.getName());
        Department updatedDepartment = departmentRepository.save(department);
        return new DepartmentDTO(updatedDepartment.getId(), updatedDepartment.getName());
    }

    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }
}
