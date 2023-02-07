package ru.acorn.taskTracker.utils;

import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.acorn.taskTracker.exception.AppEntityNotFoundException;

import java.time.LocalDateTime;

@ControllerAdvice
@Log4j
public class AdviceController {

    @ExceptionHandler(AppEntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(AppEntityNotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse(
                exception.getMessage(), LocalDateTime.now());
        log.debug(errorResponse);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
