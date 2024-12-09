package com.example.AuthUserService.exceptions;

public class TokenAlreadyExpired extends RuntimeException {
    public TokenAlreadyExpired(String message) {
        super(message);
    }
}
