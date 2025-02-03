package com.udemy.exceptions;


public class UnsupportedMathOperationException extends RuntimeException {

    private String exception;

    public UnsupportedMathOperationException(String exception) {
        super(exception);
        this.exception = exception;
    }

    public String getException() {
        return exception;
    }

}
