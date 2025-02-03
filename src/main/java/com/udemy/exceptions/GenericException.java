package com.udemy.exceptions;


import java.util.Date;

public class GenericException extends Exception{

    private Date timestamp;
    private String message;
    private String details;

    public GenericException(Date timestamp, String message, String details) {
        super(message);
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
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

}
