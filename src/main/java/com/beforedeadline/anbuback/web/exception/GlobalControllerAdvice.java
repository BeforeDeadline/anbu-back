package com.beforedeadline.anbuback.web.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler
    public ErrorResult exHandler(Exception e){
        return new ErrorResult("Exception", e.getMessage());
    }
}
