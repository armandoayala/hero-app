package com.arm.api.hero.exception;

public class DataValidationException extends RuntimeException {

    private static final String message = "Invalid data";

    public DataValidationException() {
        super(message);
    }

    public DataValidationException(String msg) {
        super(msg);
    }

    public DataValidationException(String msg, Throwable ex) {
        super(msg, ex);
    }


}
