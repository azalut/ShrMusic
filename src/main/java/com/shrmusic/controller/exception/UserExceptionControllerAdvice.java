package com.shrmusic.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class UserExceptionControllerAdvice {
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void usernamePasswordException(ConstraintViolationException exception){
        exception.getConstraintViolations().stream().forEach(
                n -> System.out.println(n.getPropertyPath().toString())
        );
    }
}
