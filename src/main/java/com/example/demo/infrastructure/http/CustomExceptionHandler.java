package com.example.demo.infrastructure.http;

import static java.util.Collections.singletonMap;

import com.example.demo.domain.exception.NotFoundException;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    private static final String ERROR = "error";

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, Object>> notFoundException(NotFoundException ex) {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(singletonMap(ERROR, ex.getMessage()));
    }

}
