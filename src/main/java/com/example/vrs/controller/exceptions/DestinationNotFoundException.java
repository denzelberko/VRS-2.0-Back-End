package com.example.vrs.controller.exceptions;

public class DestinationNotFoundException extends RuntimeException {
    public DestinationNotFoundException(Long id) {
        super("Could not find destination " + id);
    }
}
