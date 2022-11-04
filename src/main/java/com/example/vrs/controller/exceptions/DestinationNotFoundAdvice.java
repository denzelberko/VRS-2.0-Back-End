package com.example.vrs.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class DestinationNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(DestinationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String destinationNotFoundHandler(DestinationNotFoundException ex) {
        return ex.getMessage();
    }
}
