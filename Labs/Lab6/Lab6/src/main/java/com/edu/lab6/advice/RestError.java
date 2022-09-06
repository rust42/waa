package com.edu.lab6.advice;

import lombok.Data;

@Data
public class RestError {
    private String message;
    private String code;

    public RestError(String message, String code) {
        this.message = message;
        this.code = code;
    }
}
