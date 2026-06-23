package com.santiago.hexagonal.infrastructure.exception;

public class DatabaseException extends InfrastructureException {
    public DatabaseException(String message) {
        super(message);
    }

    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getCode() {
        return 500;
    }

    @Override
    public Throwable getCause() {
        return this.cause;
    }
}
