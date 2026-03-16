package com.santiago.hexagonal.application.exception;

import com.santiago.hexagonal.domain.exception.DomainException;

public abstract class ApplicationException extends DomainException {
    public ApplicationException(String message) {
        super(message);
    }

    public abstract int getCode();
}
