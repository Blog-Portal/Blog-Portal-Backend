package com.madhur.blog_portal.Exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler class to handle various exceptions in the
 * application.
 */
@RestControllerAdvice
public class GlobalError {
    /**
     * Handles the validation errors.
     * @param ex The MethodArgumentNotValidException instance containing
     *            validation errors
     * @return statusManager object which stores status and error messages
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public StatusManager handleInvalidArgument(
            final MethodArgumentNotValidException ex) {
        Map<String, String> errorMap = new HashMap<>();
        List<Object> list = new ArrayList<>();
        StatusManager statusManager = new StatusManager();
        statusManager.setStatus(HttpStatus.BAD_REQUEST);
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        list.add(errorMap);
        statusManager.setMessage(list);
        return statusManager;
    }

    /**
     * Handles the custom exceptions.
     * @param ex custom exception instance
     * @return statusManager object which stores status and error messages
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RecordNotFoundException.class)
    public StatusManager customException(final RecordNotFoundException ex) {
        StatusManager statusManager = new StatusManager();
        statusManager.setStatus(HttpStatus.NOT_FOUND);
        statusManager.setMessage(ex.getMessage());
        return statusManager;
    }

    /**
     * Handles the custom exceptions.
     * @param ex custom exception instance
     * @return statusManager object which stores status and error messages
     */
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(RecordAlreadyExistException.class)
    public StatusManager customException(final RecordAlreadyExistException ex) {
        StatusManager statusManager = new StatusManager();
        statusManager.setStatus(HttpStatus.CONFLICT);
        statusManager.setMessage(ex.getMessage());
        return statusManager;
    }

    /**
     * Handles the custom exceptions.
     * @param ex custom exception instance
     * @return statusManager object which stores status and error messages
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidRecordException.class)
    public StatusManager customException(final InvalidRecordException ex) {
        StatusManager statusManager = new StatusManager();
        statusManager.setStatus(HttpStatus.BAD_REQUEST);
        statusManager.setMessage(ex.getMessage());
        return statusManager;
    }

    /**
     * Handles the custom exceptions.
     * @param ex custom exception instance
     * @return statusManager object which stores status and error messages
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(InvalidCredentialsException.class)
    public StatusManager customException(final InvalidCredentialsException ex) {
        StatusManager statusManager = new StatusManager();
        statusManager.setStatus(HttpStatus.UNAUTHORIZED);
        statusManager.setMessage(ex.getMessage());
        return statusManager;
    }
}
