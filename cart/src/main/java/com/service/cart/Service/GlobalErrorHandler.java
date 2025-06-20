package com.service.cart.Service;

import com.service.cart.DTO.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler
    public ResponseEntity<APIResponse> handleValidationException(Exception exception) {
//        System.out.println("Here you go.. " + exception.getMessage());
        APIResponse response = new APIResponse("Error occurred in the server", exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
