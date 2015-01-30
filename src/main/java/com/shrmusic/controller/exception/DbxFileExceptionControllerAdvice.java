package com.shrmusic.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.FileNotFoundException;
import java.io.IOException;

@ControllerAdvice
public class DbxFileExceptionControllerAdvice {
    @ExceptionHandler(value = {FileNotFoundException.class, IOException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void fileNotFound(){
    }
}
