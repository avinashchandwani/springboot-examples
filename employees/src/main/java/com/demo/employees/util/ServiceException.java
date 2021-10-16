package com.demo.employees.util;

public class ServiceException extends RuntimeException {

    private int responseCode;

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(int responseCode, String message) {
        super(message);
        this.responseCode = responseCode;
    }

    public ServiceException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.responseCode = exceptionCode.getResponseCode();
    }

    public int getResponseCode() {
        return responseCode;
    }
}