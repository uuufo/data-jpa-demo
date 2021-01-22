package com.example.accessingdatajpa.exceptions;

import org.springframework.http.HttpStatus;

public class ErrorResponse {

    String message;
    String joke = "Knock, Knock!";
    int errorCode = 500;
    HttpStatus status;

    public ErrorResponse(String message) {
        this.message = message;
    }

    public ErrorResponse(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public String getJoke() {
        return joke;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
