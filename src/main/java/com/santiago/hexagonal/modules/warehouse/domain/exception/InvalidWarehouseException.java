package com.santiago.hexagonal.modules.warehouse.domain.exception;

import com.santiago.hexagonal.domain.exception.DomainException;

public class InvalidWarehouseException extends DomainException {
    public InvalidWarehouseException() {
        super("Invalid warehouse");
    }

    public InvalidWarehouseException(String message) {
        super(message);
    }

    @Override
    public int getCode() {
        return 400;
    }

    @Override
    public Throwable getCause() {
        return this.cause;
    }
}
