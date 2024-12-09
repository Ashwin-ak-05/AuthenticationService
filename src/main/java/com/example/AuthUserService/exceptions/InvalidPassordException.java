package com.example.AuthUserService.exceptions;

public class InvalidPassordException extends RuntimeException {
    public InvalidPassordException(String message) {
        super(message);
    }
}
