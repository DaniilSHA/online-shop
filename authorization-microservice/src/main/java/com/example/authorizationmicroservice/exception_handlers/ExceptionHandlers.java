package com.example.authorizationmicroservice.exception_handlers;


import com.example.authorizationmicroservice.dto.ExceptionDto;
import com.example.authorizationmicroservice.exceptions.EmailIsBusyException;
import com.example.authorizationmicroservice.exceptions.PasswordIsIncorrectException;
import com.example.authorizationmicroservice.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(EmailIsBusyException.class)
    public ResponseEntity<ExceptionDto> handleEmailIsBusyException(EmailIsBusyException e) {
        return new ResponseEntity<>(new ExceptionDto(e.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleUserNotFoundException(UserNotFoundException e) {
        return new ResponseEntity<>(new ExceptionDto(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PasswordIsIncorrectException.class)
    public ResponseEntity<ExceptionDto> handlePasswordIsIncorrectException(PasswordIsIncorrectException e) {
        return new ResponseEntity<>(new ExceptionDto(e.getMessage()), HttpStatus.UNAUTHORIZED);
    }

}
