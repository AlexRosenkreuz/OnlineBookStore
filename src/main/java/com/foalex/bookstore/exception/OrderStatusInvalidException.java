package com.foalex.bookstore.exception;

public class OrderStatusInvalidException extends RuntimeException {
    public OrderStatusInvalidException(String message) {
        super(message);
    }
}
