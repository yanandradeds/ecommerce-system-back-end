package com.andradeds.exceptions;

public class UserAlreadyInUseException extends RuntimeException{

    private String message;

    public UserAlreadyInUseException(String message) {
        super(message);
        this.message = message;
    }
}

