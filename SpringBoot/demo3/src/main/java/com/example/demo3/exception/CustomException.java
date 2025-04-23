package com.example.demo3.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class CustomException extends RuntimeException {
    private String code;
    private HttpStatus stattus;

    public CustomException(String code, HttpStatus stattus) {
        super(String.format("En %s se obtuvo el stattus %s", code, stattus));
        this.code = code;
        this.stattus = stattus;
    }
}
