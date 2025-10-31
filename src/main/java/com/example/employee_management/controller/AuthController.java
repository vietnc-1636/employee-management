package com.example.employee_management.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.employee_management.dto.RegisterDTO;
import com.example.employee_management.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final UserService userService;

    @GetMapping("/login")
    public String showLoginPage(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            Authentication authentication,
            Model model) {

        // If user is already authenticated, redirect to home
        if (authentication != null && authentication.isAuthenticated()) {
            log.info("User {} is already authenticated, redirecting to home", authentication.getName());
            return "redirect:/";
        }

        if (error != null) {
            model.addAttribute("error", "Invalid username or password");
            log.warn("Login attempt failed");
        }

        if (logout != null) {
            model.addAttribute("message", "You have been logged out successfully");
            log.info("User logged out");
        }

        return "auth/login";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("registerDTO", new RegisterDTO());
        return "auth/register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute RegisterDTO registerDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            Model model) {

        // Check for validation errors
        if (bindingResult.hasErrors()) {
            return "auth/register";
        }

        // Check if passwords match
        if (!registerDTO.getPassword().equals(registerDTO.getConfirmPassword())) {
            model.addAttribute("error", "Passwords do not match");
            return "auth/register";
        }

        // Check if username already exists
        if (userService.usernameExists(registerDTO.getUsername())) {
            model.addAttribute("error", "Username already exists");
            return "auth/register";
        }

        try {
            userService.registerUser(registerDTO);
            log.info("User registered successfully: {}", registerDTO.getUsername());
            redirectAttributes.addFlashAttribute("success", "Registration successful! Please login.");
            return "redirect:/login";
        } catch (Exception e) {
            log.error("Error registering user: {}", e.getMessage());
            model.addAttribute("error", "An error occurred during registration");
            return "auth/register";
        }
    }
}
