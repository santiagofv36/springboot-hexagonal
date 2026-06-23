package com.santiago.hexagonal.infrastructure.web.http;

import java.time.Instant;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import jakarta.servlet.http.HttpServletRequest;

import com.santiago.hexagonal.application.exception.*;
import com.santiago.hexagonal.domain.exception.DomainException;
import com.santiago.hexagonal.infrastructure.exception.InfrastructureException;

@ControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(UnauthorizedException.class)
        public ResponseEntity<ErrorResponse> handleUnauthorizedException(UnauthorizedException e) {
                ErrorResponse errorResponse = new ErrorResponse(
                                Instant.now(),
                                HttpStatus.UNAUTHORIZED.value(),
                                "Unauthorized",
                                e.getMessage());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }

        @ExceptionHandler(NoHandlerFoundException.class)
        public ResponseEntity<ErrorResponse> handleNoHandlerFound(
                        NoHandlerFoundException ex,
                        HttpServletRequest request) {

                ErrorResponse body = new ErrorResponse(
                                Instant.now(),
                                HttpStatus.UNAUTHORIZED.value(),
                                "Not Found",
                                "Endpoint not found: " + request.getRequestURI());

                return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        }

        // Domain exceptions mapped
        @ExceptionHandler(DomainException.class)
        public ResponseEntity<ErrorResponse> handleDomainException(DomainException e) {
                String exceptionType = "Domain Exception";
                if (e instanceof ApplicationException) {
                        exceptionType = "Application Exception";
                }
                if (e instanceof InfrastructureException) {
                        exceptionType = "Infrastructure Exception";
                }

                exceptionType += " - " + e.getClass().getSimpleName();

                ErrorResponse errorResponse = new ErrorResponse(
                                Instant.now(),
                                e.getCode(),
                                exceptionType,
                                e.getMessage(),
                                e.getCause() != null ? e.getCause().getMessage() : null);
                return ResponseEntity.status(HTTP_STATUS_MAP.getOrDefault(e.getCode(), HttpStatus.BAD_REQUEST))
                                .body(errorResponse);
        }

        @ExceptionHandler(HttpMessageNotReadableException.class)
        public ResponseEntity<ErrorResponse> handleUnreadableBody(HttpMessageNotReadableException ex) {
                ErrorResponse body = new ErrorResponse(
                                Instant.now(),
                                400,
                                "InvalidRequest",
                                "Request body is missing or malformed");
                return ResponseEntity.badRequest().body(body);
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<ErrorResponse> handleException(Exception e) {
                ErrorResponse errorResponse = new ErrorResponse(
                                Instant.now(),
                                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                "Internal Server Error",
                                e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }

        public static final Map<Integer, HttpStatus> HTTP_STATUS_MAP = Map.ofEntries(
                        Map.entry(400, HttpStatus.BAD_REQUEST),
                        Map.entry(401, HttpStatus.UNAUTHORIZED),
                        Map.entry(403, HttpStatus.FORBIDDEN),
                        Map.entry(404, HttpStatus.NOT_FOUND),
                        Map.entry(405, HttpStatus.METHOD_NOT_ALLOWED),
                        Map.entry(406, HttpStatus.NOT_ACCEPTABLE),
                        Map.entry(408, HttpStatus.REQUEST_TIMEOUT),
                        Map.entry(409, HttpStatus.CONFLICT),
                        Map.entry(410, HttpStatus.GONE),
                        Map.entry(411, HttpStatus.LENGTH_REQUIRED),
                        Map.entry(412, HttpStatus.PRECONDITION_FAILED),
                        Map.entry(414, HttpStatus.URI_TOO_LONG),
                        Map.entry(415, HttpStatus.UNSUPPORTED_MEDIA_TYPE),
                        Map.entry(417, HttpStatus.EXPECTATION_FAILED),
                        Map.entry(421, HttpStatus.MISDIRECTED_REQUEST),
                        Map.entry(423, HttpStatus.LOCKED),
                        Map.entry(424, HttpStatus.FAILED_DEPENDENCY),
                        Map.entry(425, HttpStatus.TOO_EARLY),
                        Map.entry(426, HttpStatus.UPGRADE_REQUIRED),
                        Map.entry(428, HttpStatus.PRECONDITION_REQUIRED),
                        Map.entry(429, HttpStatus.TOO_MANY_REQUESTS),
                        Map.entry(431, HttpStatus.REQUEST_HEADER_FIELDS_TOO_LARGE),
                        Map.entry(451, HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS),
                        Map.entry(500, HttpStatus.INTERNAL_SERVER_ERROR),
                        Map.entry(501, HttpStatus.NOT_IMPLEMENTED),
                        Map.entry(502, HttpStatus.BAD_GATEWAY),
                        Map.entry(503, HttpStatus.SERVICE_UNAVAILABLE),
                        Map.entry(504, HttpStatus.GATEWAY_TIMEOUT),
                        Map.entry(505, HttpStatus.HTTP_VERSION_NOT_SUPPORTED),
                        Map.entry(506, HttpStatus.VARIANT_ALSO_NEGOTIATES),
                        Map.entry(507, HttpStatus.INSUFFICIENT_STORAGE),
                        Map.entry(508, HttpStatus.LOOP_DETECTED),
                        Map.entry(511, HttpStatus.NETWORK_AUTHENTICATION_REQUIRED));

}
