package com.santiago.hexagonal.infrastructure.exception;

public class DatabaseException extends InfrastructureException {
    public DatabaseException(String message) {
        super(message);
    }

    @Override
    public int getCode() {
        return 500;
    }
}
