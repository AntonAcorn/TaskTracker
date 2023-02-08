package ru.acorn.taskTracker.utils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import ru.acorn.taskTracker.exception.AppEntityNotFoundException;

import java.util.List;

public class ErrorUtils {
    public static void returnError (BindingResult bindingResult){
        StringBuilder errorBuilder = new StringBuilder();
        List<FieldError> errors = bindingResult.getFieldErrors();
        for (FieldError error: errors
             ) {
            errorBuilder.append(error.getField())
                    .append(" - ")
                    .append(error.getDefaultMessage())
                    .append("; ");
        }
        throw new AppEntityNotFoundException(errorBuilder.toString());
    }
}
