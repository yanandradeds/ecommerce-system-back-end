package com.udemy.exceptions;


import java.util.Date;

public class GenericException {

    private Date timestamp;
    private String message;
    private String details;
    private int statusCode;

    public GenericException(Date timestamp, String message, String details, int statusCode) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
        this.statusCode = statusCode;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

    public int getStatus() {
        return statusCode;
    }
}
