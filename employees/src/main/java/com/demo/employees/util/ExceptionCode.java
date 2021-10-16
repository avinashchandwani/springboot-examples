package com.demo.employees.util;

public enum ExceptionCode {
    BAD_REQUEST(400, "Input is invalid or missing, please check"),
    UNAUTHORIZED(401, "Invalid credentials or token expired, please login again"),
    FORBIDDEN(403, "Permission denied, please check with your administrator"),
    NOT_FOUND(404, "The requested resource not found"),
    ALREADY_EXISTS(409, "Record already exists"),
    INTERNAL_FAILURE(500, "Internal failure while saving your record"),
    API_NOT_IMPLEMENTED(501, "This API is not implemented");
    private final int responseCode;
    private final String message;

    ExceptionCode(int responseCode, String message) {
        this.responseCode = responseCode;
        this.message = message;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getMessage() {
        return message;
    }
}
