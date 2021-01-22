package com.example.accessingdatajpa.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = JobException.class)
    protected ResponseEntity<ErrorResponse> handleJobException(JobException ex) {
        return new ResponseEntity<ErrorResponse>(new ErrorResponse(ex.getMessage()), ex.getStatus());
    }

    @ExceptionHandler(value = EmployeeException.class)
    protected ResponseEntity<ErrorResponse> handleEmployeeException(EmployeeException ex) {
        return new ResponseEntity<ErrorResponse>(new ErrorResponse(ex.getMessage()), HttpStatus.NOT_FOUND);
    }
}
