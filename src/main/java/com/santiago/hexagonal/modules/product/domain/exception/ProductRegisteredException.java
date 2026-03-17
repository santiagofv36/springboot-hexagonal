package com.santiago.hexagonal.modules.product.domain.exception;

import com.santiago.hexagonal.domain.exception.DomainException;

public class ProductRegisteredException extends DomainException {
    public ProductRegisteredException() {
        super("Product already registered");
    }

    public ProductRegisteredException(String message) {
        super(message);
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
