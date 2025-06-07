package com.service.user_management.Service;

import com.service.user_management.DTO.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler
    private ResponseEntity<APIResponse> handleValidationException(Exception exception) {
        System.out.println("<<<Error occurred>>> : " + exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new APIResponse("Error occurred", exception.getMessage()));
    }
}
