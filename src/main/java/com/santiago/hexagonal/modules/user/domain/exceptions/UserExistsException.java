package com.santiago.hexagonal.modules.user.domain.exceptions;

import com.santiago.hexagonal.domain.exception.DomainException;

public class UserExistsException extends DomainException {
    protected Throwable cause;

    public UserExistsException(String message) {
        super(message);
        this.cause = null;
    }

    public UserExistsException(String message, Throwable cause) {
        super(message);
        this.cause = cause;
    }

    @Override
    public int getCode() {
        return 409;
    }

    @Override
    public Throwable getCause() {
        return this.cause;
    }
}
