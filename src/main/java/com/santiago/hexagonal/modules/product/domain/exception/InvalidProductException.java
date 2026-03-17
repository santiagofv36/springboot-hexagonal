package com.santiago.hexagonal.modules.product.domain.exception;

import com.santiago.hexagonal.domain.exception.DomainException;

public class InvalidProductException extends DomainException {
    public InvalidProductException() {
        super("Invalid product");
    }

    public InvalidProductException(String message) {
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
