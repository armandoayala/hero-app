package com.arm.api.hero.exception;

public class NotFoundException extends RuntimeException {

    private static final String message = "Entity not found";

    public NotFoundException() {
        super(message);
    }

    public NotFoundException(String msg) {
        super(msg);
    }

    public NotFoundException(String msg, Throwable ex) {
        super(msg, ex);
    }

}
