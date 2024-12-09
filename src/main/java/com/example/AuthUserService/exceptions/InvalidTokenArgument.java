package com.example.AuthUserService.exceptions;

public class InvalidTokenArgument extends RuntimeException {
    public InvalidTokenArgument(String message) {
        super(message);
    }
}
