package com.service.product.configuration;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.service.DTO.APIResponse;

@ControllerAdvice // handle all exceptions..
public class GlobalErrorHandler {

    @ExceptionHandler(value = { IOException.class, JsonProcessingException.class })
    public ResponseEntity<APIResponse> handleValidationException(Exception errorException) {
        System.err.println(errorException.getMessage());

        APIResponse response = new APIResponse("", "");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
    // new APIResponse(errorException.getMessage(), errorException.getCause())
}
