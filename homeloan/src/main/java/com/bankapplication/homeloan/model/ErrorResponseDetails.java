package com.bankapplication.homeloan.model;

import java.time.LocalDateTime;

public record ErrorResponseDetails(String message, int status, LocalDateTime timestamp) {
    public ErrorResponseDetails(String message, int status) {
        this(message, status, LocalDateTime.now());
    }
}
