package com.santiago.hexagonal.infrastructure.exception;

import com.santiago.hexagonal.application.exception.ApplicationException;

public abstract class InfrastructureException extends ApplicationException {

    public InfrastructureException(String message) {
        super(message);
        this.cause = null;
    }

    public InfrastructureException(String message, Throwable cause) {
        super(message);
        this.cause = cause;
    }

    public abstract int getCode();

    public abstract Throwable getCause();
}
