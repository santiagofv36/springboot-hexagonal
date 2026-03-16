package com.santiago.hexagonal.infrastructure.exception;

import com.santiago.hexagonal.application.exception.ApplicationException;

public abstract class InfrastructureException extends ApplicationException {
    public InfrastructureException(String message) {
        super(message);
    }

    public abstract int getCode();
}
