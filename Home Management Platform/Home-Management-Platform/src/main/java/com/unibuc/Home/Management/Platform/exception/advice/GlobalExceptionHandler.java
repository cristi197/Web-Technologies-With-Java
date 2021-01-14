package com.unibuc.Home.Management.Platform.exception.advice;

import com.unibuc.Home.Management.Platform.exception.PersonNotFoundException;
import com.unibuc.Home.Management.Platform.exception.TaskNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({PersonNotFoundException.class})
    public ResponseEntity<String> handle(PersonNotFoundException e){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage() + " at " + LocalDateTime.now());
    }
    @ExceptionHandler({TaskNotFoundException.class})
    public ResponseEntity<String> handle(TaskNotFoundException e){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage() + " at " + LocalDateTime.now());
    }
}
