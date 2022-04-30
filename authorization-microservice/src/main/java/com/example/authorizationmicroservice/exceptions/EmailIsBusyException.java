package com.example.authorizationmicroservice.exceptions;

public class EmailIsBusyException extends RuntimeException {
    public EmailIsBusyException(String msg) {
        super(msg);
    }
}
