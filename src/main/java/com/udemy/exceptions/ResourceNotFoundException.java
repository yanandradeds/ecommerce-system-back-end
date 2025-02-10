package com.udemy.exceptions;


public class ResourceNotFoundException extends RuntimeException {

    private String exception;

    public ResourceNotFoundException(String exception) {
        super(exception);
        this.exception = exception;
    }

    public String getException() {
        return exception;
    }

    @Override
    public synchronized Throwable initCause(Throwable cause) {
        return super.initCause(null);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
