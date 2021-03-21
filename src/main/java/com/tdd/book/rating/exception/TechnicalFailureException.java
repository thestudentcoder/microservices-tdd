package com.tdd.book.rating.exception;

public class TechnicalFailureException extends RuntimeException {

    public TechnicalFailureException(String message) {
        super(message);
    }
}
