package com.santiago.hexagonal.application.exception;

import com.santiago.hexagonal.domain.exception.DomainException;

public abstract class ApplicationException extends DomainException {
    protected Throwable cause;

    public ApplicationException(String message) {
        super(message);
        this.cause = null;
    }

    public ApplicationException(String message, Throwable cause) {
        super(message);
        this.cause = cause;
    }

    public abstract int getCode();

    public abstract Throwable getCause();
}
