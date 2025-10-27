package com.example.employee_management.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
}
