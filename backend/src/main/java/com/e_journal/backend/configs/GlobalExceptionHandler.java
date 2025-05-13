package com.e_journal.backend.configs;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ProblemDetail;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, List<String>> errors = new HashMap<>();

        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            String field = fieldError.getField();
            String message = fieldError.getDefaultMessage();

            errors.computeIfAbsent(field, key -> new ArrayList<>()).add(message);
        }

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({
            BadCredentialsException.class,
            AccountStatusException.class,
            AccessDeniedException.class,
            SignatureException.class,
            ExpiredJwtException.class
    })
    public ResponseEntity<Map<String, List<String>>> handleJwtErrors(Exception exception) {
        String field = "auth";
        String message;

        if (exception instanceof BadCredentialsException) {
            message = "Incorrect username or password";
        } else if (exception instanceof AccountStatusException) {
            message = "Account is locked or disabled";
        } else if (exception instanceof AccessDeniedException) {
            message = "Access denied";
        } else if (exception instanceof SignatureException) {
            message = "Invalid JWT signature";
        } else if (exception instanceof ExpiredJwtException) {
            message = "JWT token has expired";
        } else {
            message = "Authentication error";
        }

        Map<String, List<String>> errors = new HashMap<>();
        errors.put(field, List.of(message));

        return new ResponseEntity<>(errors, HttpStatus.UNAUTHORIZED);
    }
}
