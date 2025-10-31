package com.example.employee_management.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.employee_management.dto.DepartmentDTO;
import com.example.employee_management.service.DepartmentService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/departments")
@Slf4j
public class DepartmentController {

    private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping()
    public String index(Model model) {
        List<DepartmentDTO> departments = departmentService.getAllDepartments();
        model.addAttribute("departments", departments);
        model.addAttribute("department", new DepartmentDTO());
        return "department/index";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public String create(
            @Valid @ModelAttribute("department") DepartmentDTO departmentDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            Model model) {

        log.info("Creating department: {}", departmentDTO);

        // Validation errors
        if (bindingResult.hasErrors()) {
            log.warn("Validation failed: {}", bindingResult.getAllErrors());

            // Re-populate departments list for display
            List<DepartmentDTO> departments = departmentService.getAllDepartments();
            model.addAttribute("departments", departments);

            // Return to same page with errors
            return "department/index";
        }

        try {
            departmentService.create(departmentDTO);
            redirectAttributes.addFlashAttribute("success",
                    "Department '" + departmentDTO.getName() + "' created successfully!");
            log.info("Department created successfully: {}", departmentDTO.getName());
        } catch (Exception e) {
            log.error("Failed to create department", e);
            redirectAttributes.addFlashAttribute("error",
                    "Failed to create department: " + e.getMessage());
        }

        return "redirect:/departments";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}")
    public String delete(@PathVariable Long id) {

        log.info("Deleting department: {}", id);

        try {
            departmentService.delete(id);
            log.info("Department deleted successfully: {}", id);
        } catch (Exception e) {
            log.error("Failed to delete department {}", id, e);
        }

        return "redirect:/departments";
    }

}
