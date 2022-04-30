package com.example.authorizationmicroservice.exceptions;

public class PasswordIsIncorrectException extends RuntimeException {
    public PasswordIsIncorrectException(String msg) {
        super(msg);
    }
}
