package com.santiago.hexagonal.modules.inventory.domain.exception;

import com.santiago.hexagonal.domain.exception.DomainException;

public class QuantityBelowZeroException extends DomainException {
    public QuantityBelowZeroException() {
        super("Quantity cannot be negative");
    }

    @Override
    public int getCode() {
        return 400;
    }

    @Override
    public String getMessage() {
        return "Quantity cannot be negative";
    }

    @Override
    public Throwable getCause() {
        return cause;
    }
}
