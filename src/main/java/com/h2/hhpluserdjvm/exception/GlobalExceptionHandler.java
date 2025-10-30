package com.h2.hhpluserdjvm.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e) {
        ErrorResponse response = new ErrorResponse(
            e.getErrorCode().getCode(),
            e.getMessage()
        );
        return ResponseEntity
            .status(e.getErrorCode().getStatus())
            .body(response);
    }
}