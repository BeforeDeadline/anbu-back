package com.beforedeadline.anbuback.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ResponseStatus(HttpStatus.ACCEPTED)
    @ExceptionHandler
    public ErrorResult exHandler(Exception e){
        return new ErrorResult(e.getClass().getSimpleName(), e.getMessage());
    }
}
