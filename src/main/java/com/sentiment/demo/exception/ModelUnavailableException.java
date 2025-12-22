package com.sentiment.demo.exception;

public class ModelUnavailableException extends RuntimeException {
    public ModelUnavailableException(String message, Throwable cause) {
        super(message, cause);
    }

    public ModelUnavailableException(String message) {
        super(message);
    }
}
