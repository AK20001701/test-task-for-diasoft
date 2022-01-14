package ru.diasoft.testTask.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.diasoft.testTask.exception.AlreadyTakenException;
import ru.diasoft.testTask.exception.UserNotFoundException;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(value = {
            UserNotFoundException.class,
    })
    protected ResponseEntity<Object> handleNotFound(RuntimeException ex) {
        return handle(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {
            AlreadyTakenException.class,
    })
    protected ResponseEntity<Object> handleBadRequest(RuntimeException ex) {
        return handle(ex, HttpStatus.CONFLICT);
    }

    protected ResponseEntity<Object> handle(RuntimeException ex, HttpStatus httpStatus) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, httpStatus);
    }
}
