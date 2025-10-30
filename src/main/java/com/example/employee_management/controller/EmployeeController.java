package com.example.employee_management.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.employee_management.dto.EmployeeDTO;
import com.example.employee_management.service.DepartmentService;
import com.example.employee_management.service.EmployeeService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/employees")
@Slf4j
public class EmployeeController {

    private EmployeeService employeeService;
    private DepartmentService departmentService;

    public EmployeeController(EmployeeService employeeService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    @GetMapping()
    public String index(@RequestParam(required = false) String keyword, Model model) {
        List<EmployeeDTO> employees = employeeService.getEmployees(keyword);
        model.addAttribute("employees", employees);
        return "employee/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("employee", new EmployeeDTO());
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "employee/create";
    }

    @PostMapping()
    public String create(@Valid @ModelAttribute("employee") EmployeeDTO employeeDTO,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        log.info("Creating employee: {}", employeeDTO);

        if (bindingResult.hasErrors()) {
            model.addAttribute("departments", departmentService.getAllDepartments());
            return "employee/create";
        }

        try {
            employeeService.create(employeeDTO);
            log.info("Employee created successfully: {}", employeeDTO.getName());
            return "redirect:/employees";
        } catch (Exception e) {
            log.error("Failed to create employee", e);
            redirectAttributes.addFlashAttribute("error",
                    "Failed to create employee: " + e.getMessage());
            model.addAttribute("departments", departmentService.getAllDepartments());
            return "employee/create";
        }
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        try {
            EmployeeDTO employeeDTO = employeeService.getEmployeeById(id);
            model.addAttribute("employee", employeeDTO);
            model.addAttribute("departments", departmentService.getAllDepartments());
            return "employee/edit";
        } catch (Exception e) {
            log.error("Failed to load employee for edit", e);
            return "redirect:/employees";
        }
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id,
            @Valid @ModelAttribute("employee") EmployeeDTO employeeDTO,
            BindingResult bindingResult,
            Model model) {

        log.info("Updating employee: {}", employeeDTO);

        if (bindingResult.hasErrors()) {
            model.addAttribute("departments", departmentService.getAllDepartments());
            return "employee/edit";
        }

        try {
            employeeService.update(id, employeeDTO);
            log.info("Employee updated successfully: {}", employeeDTO.getName());
            return "redirect:/employees";
        } catch (Exception e) {
            log.error("Failed to update employee", e);
            model.addAttribute("departments", departmentService.getAllDepartments());
            return "employee/edit";
        }
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {

        log.info("Deleting employee: {}", id);

        try {
            employeeService.delete(id);
            log.info("Employee deleted successfully: {}", id);
        } catch (Exception e) {
            log.error("Failed to delete employee", e);
        }

        return "redirect:/employees";
    }
}
