package com.example.employee_management.exception;

import lombok.Getter;

@Getter
public class ModelNotFoundException extends RuntimeException {
    private final int code;

    public ModelNotFoundException(String message, int code) {
        super(message);
        this.code = code;
    }

}
