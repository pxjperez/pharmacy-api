package com.ejemplo.api.exception;

import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(RecursoNoEncontradoException.class)
    ResponseEntity<Map<String, Object>> noEncontrado(RecursoNoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("timestamp", Instant.now(), "status", 404, "message", ex.getMessage()));
    }

    @ExceptionHandler(RecursoDuplicadoException.class)
    ResponseEntity<Map<String, Object>> duplicado(RecursoDuplicadoException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Map.of("timestamp", Instant.now(), "status", 409, "message", ex.getMessage()));
    }

    @ExceptionHandler(EntradaInvalidaException.class)
    ResponseEntity<Map<String, Object>> entradaInvalida(EntradaInvalidaException ex) {
        return ResponseEntity.badRequest()
                .body(Map.of("timestamp", Instant.now(), "status", 400, "message", ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<Map<String, Object>> validacion(MethodArgumentNotValidException ex) {
        return ResponseEntity.badRequest()
                .body(Map.of("timestamp", Instant.now(), "status", 400, "message", "Solicitud inválida"));
    }
}
