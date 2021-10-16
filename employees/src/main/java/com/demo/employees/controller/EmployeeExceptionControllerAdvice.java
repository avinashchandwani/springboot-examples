package com.demo.employees.controller;

import com.demo.employees.util.ServiceException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

import static com.demo.employees.util.ExceptionCode.*;


@ControllerAdvice
public class EmployeeExceptionControllerAdvice {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({DataIntegrityViolationException.class, ConstraintViolationException.class})
    public ResponseEntity handleConflict() {
        return new ResponseEntity<>(getMessageBody(ALREADY_EXISTS.getMessage()), HttpStatus.valueOf(ALREADY_EXISTS.getResponseCode()));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({EmptyResultDataAccessException.class})
    public ResponseEntity handleRecordNotFoundException() {
        return new ResponseEntity<>(getMessageBody(NOT_FOUND.getMessage()), HttpStatus.valueOf(NOT_FOUND.getResponseCode()));
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity handleInvalidAccess() {
        return new ResponseEntity<>(getMessageBody(FORBIDDEN.getMessage()), HttpStatus.valueOf(FORBIDDEN.getResponseCode()));
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity handleInvalidData(ServiceException ex) {
        return new ResponseEntity<>(getMessageBody(ex.getMessage()), HttpStatus.valueOf(ex.getResponseCode()));
    }

    @ExceptionHandler({HttpMessageNotReadableException.class, UnrecognizedPropertyException.class})
    public ResponseEntity handleIncorrectDataFormatException() {
        return new ResponseEntity<>(getMessageBody("Problems with input data provided"), HttpStatus.BAD_REQUEST);
    }

    private Map getMessageBody(String message) {
        Map body = new HashMap<>();
        body.put("message", message);
        return body;
    }
}
