package com.td.notification_serivce.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {

        Map<String, String> errors =new HashMap<>();
        List<ObjectError> errorList = ex.getBindingResult().getAllErrors();

        errorList.forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName,message);
        });
        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }
}
