package com.example.employee_management.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Object message = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                log.warn("404 error: {}", request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI));
                model.addAttribute("error", "Page Not Found");
                model.addAttribute("message", "The page you are looking for does not exist.");
                model.addAttribute("status", statusCode);
                return "error/404";
            } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
                log.warn("403 error: Access denied");
                model.addAttribute("error", "Access Denied");
                model.addAttribute("message", "You don't have permission to access this resource.");
                model.addAttribute("status", statusCode);
                return "error/403";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                log.error("500 error: {}", message);
                model.addAttribute("error", "Internal Server Error");
                model.addAttribute("message", "An unexpected error occurred. Please try again later.");
                model.addAttribute("status", statusCode);
                return "error/500";
            }
        }

        // Default error page
        log.error("Unhandled error: {}", message);
        model.addAttribute("error", "Error");
        model.addAttribute("message", "An error occurred.");
        return "error/500";
    }
}
