package com.example.accessingdatajpa.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class JobException extends ResponseStatusException {

    public JobException(String message, HttpStatus status) {
        super(status, message);
    }
}
