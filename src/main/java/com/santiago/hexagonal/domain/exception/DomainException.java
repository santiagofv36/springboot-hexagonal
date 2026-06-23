package com.santiago.hexagonal.domain.exception;

public abstract class DomainException extends RuntimeException {
    protected final Throwable cause;

    public DomainException(String message) {
        super(message);
        this.cause = null;
    }

    public DomainException(String message, Throwable cause) {
        super(message);
        this.cause = cause;
    }

    public abstract int getCode();

    public abstract Throwable getCause();
}
