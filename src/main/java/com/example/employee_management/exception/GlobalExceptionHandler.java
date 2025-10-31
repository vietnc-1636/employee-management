package com.example.employee_management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

        /**
         * Handle Access Denied (403) - Spring Security authorization failures
         */
        @ExceptionHandler(AccessDeniedException.class)
        public String handleAccessDenied(AccessDeniedException ex, Model model) {
                log.warn("Access denied: {}", ex.getMessage());
                model.addAttribute("error", "Access Denied");
                model.addAttribute("message", "You don't have permission to access this resource.");
                model.addAttribute("status", HttpStatus.FORBIDDEN.value());
                return "error/403";
        }

        /**
         * Handle Spring Security Authorization Denied Exception
         */
        @ExceptionHandler(org.springframework.security.authorization.AuthorizationDeniedException.class)
        public String handleAuthorizationDenied(
                        org.springframework.security.authorization.AuthorizationDeniedException ex,
                        Model model) {
                log.warn("Authorization denied: {}", ex.getMessage());
                model.addAttribute("error", "Access Denied");
                model.addAttribute("message", "You don't have permission to perform this action.");
                model.addAttribute("status", HttpStatus.FORBIDDEN.value());
                return "error/403";
        }

        /**
         * Handle Model Not Found (404)
         */
        @ExceptionHandler(ModelNotFoundException.class)
        public String handleModelNotFound(ModelNotFoundException ex, Model model) {
                log.error("Model not found: {}", ex.getMessage());
                model.addAttribute("error", "Not Found");
                model.addAttribute("message", ex.getMessage());
                model.addAttribute("status", HttpStatus.NOT_FOUND.value());
                return "error/404";
        }

        /**
         * Handle No Handler Found (404)
         */
        @ExceptionHandler(NoHandlerFoundException.class)
        public String handleNoHandlerFound(NoHandlerFoundException ex, Model model) {
                log.warn("No handler found: {}", ex.getRequestURL());
                model.addAttribute("error", "Page Not Found");
                model.addAttribute("message", "The page you are looking for does not exist.");
                model.addAttribute("status", HttpStatus.NOT_FOUND.value());
                return "error/404";
        }

        /**
         * Handle all uncaught exceptions (500)
         */
        @ExceptionHandler(Exception.class)
        public String handleGlobalException(Exception ex, Model model) {
                log.error("Unexpected error occurred", ex);
                model.addAttribute("error", "Internal Server Error");
                model.addAttribute("message", "An unexpected error occurred. Please try again later.");
                model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
                return "error/500";
        }
}
