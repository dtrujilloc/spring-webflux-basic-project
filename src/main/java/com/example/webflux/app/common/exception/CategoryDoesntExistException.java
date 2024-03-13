package com.example.webflux.app.common.exception;

public class CategoryDoesntExistException extends RuntimeException {

    public CategoryDoesntExistException(String message) {
        super(message);
    }
}
