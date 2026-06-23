package com.santiago.hexagonal.application.exception;

public class ResourceNotFoundException extends ApplicationException {
    public ResourceNotFoundException(String message) {
        super(message);
    }

    @Override
    public int getCode() {
        return 404;
    }

    @Override
    public Throwable getCause() {
        return this.cause;
    }
}
