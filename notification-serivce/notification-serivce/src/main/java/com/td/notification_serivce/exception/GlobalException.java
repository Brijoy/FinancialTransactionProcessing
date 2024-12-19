package com.td.notification_serivce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NotificationException.class)
    public ResponseEntity<ErrorClass> reponseEntity(NotificationException rteee, WebRequest webRequest){

        ErrorClass errorObj = new ErrorClass(
                rteee.getMessage(),
                LocalDateTime.now(),
                webRequest.getDescription(true),
                "Notification Not found"
        );

        return new ResponseEntity<>(errorObj, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorClass> reponseEntity(UserException rteee, WebRequest webRequest){

        ErrorClass errorObj = new ErrorClass(
                rteee.getMessage(),
                LocalDateTime.now(),
                webRequest.getDescription(true),
                "User Not found"
        );

        return new ResponseEntity<>(errorObj, HttpStatus.NOT_FOUND);

    }

}
