package com.university.demo.config.exception.handler;

import com.university.demo.config.beans.ApiError;
import com.university.demo.config.exception.exceptions.NotRecordInDataBaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiError> handleEntityNotFoundException(EntityNotFoundException entityNotFoundException){
        String notFoundId = entityNotFoundException.getMessage().split("id")[1];
        String errorMessage  = "No register was found by the id " + notFoundId;
        ApiError apiError = new ApiError(HttpStatus.CONFLICT, errorMessage + notFoundId);
        return new ResponseEntity<ApiError>(apiError, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotRecordInDataBaseException.class)
    public ResponseEntity<ApiError> handleEntityNotFoundException(NotRecordInDataBaseException notRecordInDataBaseException){
        ApiError apiError = new ApiError(HttpStatus.CONFLICT, notRecordInDataBaseException.getMessage());
        return new ResponseEntity<ApiError>(apiError, HttpStatus.CONFLICT);
    }


}
