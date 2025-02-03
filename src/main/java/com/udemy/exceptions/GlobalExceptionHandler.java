package com.udemy.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import java.util.Date;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<GenericException> globalHandlerException(Exception ex, WebRequest request) {
        GenericException internalException = new GenericException(new Date(),ex.getMessage(),request.getDescription(false));

        return new ResponseEntity<GenericException>(internalException, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UnsupportedMathOperationException.class)
    public ResponseEntity<GenericException> invalidMathOperationException(Exception ex, WebRequest request) {

        GenericException localException = new GenericException(new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(localException, HttpStatus.BAD_REQUEST);
    }
}
