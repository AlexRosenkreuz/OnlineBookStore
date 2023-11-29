package com.foalex.bookstore.exception;

import java.time.LocalDateTime;

public record ErrorResponseWrapper(LocalDateTime time, String error, String details) {
}
