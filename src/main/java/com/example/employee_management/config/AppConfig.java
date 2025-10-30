package com.example.employee_management.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@Configuration
public class AppConfig {

    /**
     * Creates and configures a {@link PasswordEncoder} bean using
     * {@link BCryptPasswordEncoder}.
     * <p>
     * This bean is used for encoding and verifying user passwords securely
     * with the BCrypt hashing algorithm.
     *
     * @return a {@link PasswordEncoder} instance for password hashing
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Bean to map between objects (DTO <-> Entity).
     * ModelMapper helps convert data between layers easily.
     *
     * @return ModelMapper instance
     */
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        return modelMapper;
    }

    /**
     * Enable support for HTTP method override via _method parameter.
     * Allows PUT, DELETE methods in HTML forms.
     */
    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new HiddenHttpMethodFilter();
    }
}
