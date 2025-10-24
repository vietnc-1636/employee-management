package com.example.employee_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.employee_management.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
