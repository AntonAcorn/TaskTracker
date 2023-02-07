package ru.acorn.taskTracker.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.acorn.taskTracker.exception.EntityNotFoundException;
import ru.acorn.taskTracker.utils.ErrorResponse;

import java.time.LocalDateTime;

@ControllerAdvice
public class AdviceController {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException (EntityNotFoundException exception){
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
