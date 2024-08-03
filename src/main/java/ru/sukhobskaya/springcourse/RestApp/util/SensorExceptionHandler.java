package ru.sukhobskaya.springcourse.RestApp.util;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

public interface SensorExceptionHandler {

    @ExceptionHandler
    default @NotNull ResponseEntity<ErrorResponse> handleException(@NotNull SensorException e) {
        var response = new ErrorResponse(e.getMessage(), Instant.now());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
