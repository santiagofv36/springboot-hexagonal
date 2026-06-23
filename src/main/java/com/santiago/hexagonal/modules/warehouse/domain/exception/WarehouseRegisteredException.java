package com.santiago.hexagonal.modules.warehouse.domain.exception;

import com.santiago.hexagonal.domain.exception.DomainException;

public class WarehouseRegisteredException extends DomainException {
    public WarehouseRegisteredException() {
        super("Warehouse already registered");
    }

    public WarehouseRegisteredException(String message) {
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
