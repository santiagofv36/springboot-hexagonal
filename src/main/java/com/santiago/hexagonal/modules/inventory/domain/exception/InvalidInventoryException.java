package com.santiago.hexagonal.modules.inventory.domain.exception;

import com.santiago.hexagonal.domain.exception.DomainException;

public class InvalidInventoryException extends DomainException {
    public InvalidInventoryException(String message) {
        super(message);
    }

    @Override
    public int getCode() {
        return 400;
    }

    @Override
    public Throwable getCause() {
        return cause;
    }
}
