package com.santiago.hexagonal.infrastructure.web.http;

import java.time.Instant;

public record ErrorResponse(
        Instant timestamp,
        int status,
        String error,
        String message) {

}
