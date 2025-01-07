package com.td.notification_serivce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalException  {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorClass> reponseEntity(ResourceNotFoundException rteee, WebRequest webRequest){

        ErrorClass errorObj = new ErrorClass(
                rteee.getMessage(),
                LocalDateTime.now(),
                webRequest.getDescription(true),
                "User Not found"
        );

        return new ResponseEntity<>(errorObj, HttpStatus.NOT_FOUND);

    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorClass> reponseEntity(Exception rteee, WebRequest webRequest) {

        ErrorClass errorObj = new ErrorClass(
                rteee.getMessage(),
                LocalDateTime.now(),
                webRequest.getDescription(true),
                "Internal Exception Error"
                /*"Username and Password not accepted. For more information, go to\n" +
                        "535 5.7.8  https://support.google.com/mail/?p=BadCredentials d9443c01a7336-219dca028aesm292021325ad.264 - gsmtp"*/
        );

        return new ResponseEntity<>(errorObj, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
