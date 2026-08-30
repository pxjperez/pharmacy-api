package com.ejemplo.api.exception;

public class EntradaInvalidaException extends RuntimeException {
    public EntradaInvalidaException(String message) {
        super(message);
    }
}