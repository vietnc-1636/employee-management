package com.example.employee_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.employee_management.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // Custom query method to find employees by keyword in employee name or
    // department name
    List<Employee> findByNameContainingIgnoreCaseOrDepartmentNameContainingIgnoreCase(
            String name,
            String departmentName);

    default List<Employee> findByKeyword(String keyword) {
        return findByNameContainingIgnoreCaseOrDepartmentNameContainingIgnoreCase(
                keyword,
                keyword);
    }
}
