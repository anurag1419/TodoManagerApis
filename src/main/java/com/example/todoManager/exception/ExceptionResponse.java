package com.example.todoManager.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionResponse {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponseMessage> handlerResourceNotFoundException(RuntimeException runtimeException){
        log.info("Error finding the todo");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponseMessage(runtimeException.getMessage(),"FAILURE"));
    }
}
