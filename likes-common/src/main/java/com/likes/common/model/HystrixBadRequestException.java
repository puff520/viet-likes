package com.likes.common.model;

public class HystrixBadRequestException extends RuntimeException {
    private static final long serialVersionUID = -8341452103561805856L;

    public HystrixBadRequestException(String message) {
        super(message);
    }

    public HystrixBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
