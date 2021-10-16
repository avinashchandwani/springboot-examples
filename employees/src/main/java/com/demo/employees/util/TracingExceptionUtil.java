package com.demo.employees.util;

import lombok.SneakyThrows;
import org.springframework.dao.DataIntegrityViolationException;

import java.io.IOException;

public class TracingExceptionUtil {

    @SneakyThrows
    public static void throwAppropriateException(Exception ex) {
        if (ex instanceof ServiceException) {
            ServiceException e = (ServiceException) ex;
            throw new ServiceException(e.getResponseCode(), ex.getMessage());
        } else if (ex instanceof DataIntegrityViolationException) {
            throw new ServiceException(ExceptionCode.ALREADY_EXISTS);
        } else if (ex instanceof IOException || ex instanceof IllegalArgumentException) {
            throw new ServiceException(ExceptionCode.BAD_REQUEST);
        } else {
            throw new ServiceException(ExceptionCode.INTERNAL_FAILURE);
        }
    }
}