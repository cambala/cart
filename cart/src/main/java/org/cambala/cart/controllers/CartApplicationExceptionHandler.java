package org.cambala.cart.controllers;

import jakarta.servlet.ServletException;
import lombok.extern.slf4j.Slf4j;
import org.cambala.exception.ApplicationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.format.DateTimeParseException;
import java.util.UUID;

@Slf4j
@ControllerAdvice
public class CartApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    private record ErrorResponse(String message) {}

    @ExceptionHandler(value = { ApplicationException.class })
    protected ResponseEntity<ErrorResponse> resolve(ApplicationException ex) {
        if (ex.statusCode < 500) {
            if (ex.hideStackTrace) {
                log.error(ex.getMessage());
            } else {
                log.error("Ошибка при выполнении запроса:", ex);
            }
            return ResponseEntity.status(ex.statusCode).body(new ErrorResponse(ex.getMessage()));
        }
        log.error("Ошибка при выполнении запроса:", ex);
        return ResponseEntity.status(ex.statusCode).body(new ErrorResponse("Ошибка выполнения запроса. Повторите попытку позднее"));
    }

    @ExceptionHandler(value = { ServletException.class, MethodArgumentTypeMismatchException.class, DateTimeParseException.class, HttpMessageConversionException.class })
    protected ResponseEntity<ErrorResponse> requestParsingException(Exception ex) {
        var exceptionId = UUID.randomUUID();
        log.error("Ошибка обработки запроса: {}", exceptionId, ex);
        return ResponseEntity.status(400).body(new ErrorResponse("Ошибка выполнения запроса. Идентификатор ошибки: " + exceptionId));
    }
}
