package com.foalex.bookstore.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Map<Class<? extends RuntimeException>, HttpStatus> HTTP_STATUS_MAP;

    static {
        HTTP_STATUS_MAP = new HashMap<>();
        HTTP_STATUS_MAP.put(EntityNotFoundException.class, HttpStatus.NOT_FOUND);
        HTTP_STATUS_MAP.put(DataProcessingException.class, HttpStatus.BAD_REQUEST);
        HTTP_STATUS_MAP.put(DataIntegrityViolationException.class, HttpStatus.CONFLICT);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatusCode status, WebRequest request
    ) {
        Map<String, Object> body = createResponseBody(HttpStatus.BAD_REQUEST,
                ex.getBindingResult().getAllErrors());
        return new ResponseEntity<>(body, headers, status);
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFoundException(RuntimeException ex) {
        HttpStatus status = determineHttpStatus(ex);
        Map<String, Object> body = createResponseBody(status, List.of(ex));
        return new ResponseEntity<>(body, status);
    }

    @ExceptionHandler(value = DataProcessingException.class)
    protected ResponseEntity<Object> handleDataProcessingException(RuntimeException ex) {
        HttpStatus status = determineHttpStatus(ex);
        Map<String, Object> body = createResponseBody(status, List.of(ex));
        return new ResponseEntity<>(body, status);
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    protected ResponseEntity<Object> handleDataIntegrityViolationException(RuntimeException ex) {
        HttpStatus status = determineHttpStatus(ex);
        Map<String, Object> body = createResponseBody(status, List.of(ex));
        return new ResponseEntity<>(body, status);
    }

    private Map<String, Object> createResponseBody(HttpStatus status, List<?> errors) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status);
        List<String> messages = errors.stream()
                                      .map(err -> err instanceof ObjectError
                                              ? getErrorMessage((ObjectError) err)
                                              : ((RuntimeException) err).getMessage())
                                      .toList();
        body.put("errors", messages);
        return body;
    }

    private HttpStatus determineHttpStatus(RuntimeException ex) {
        return HTTP_STATUS_MAP.getOrDefault(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private String getErrorMessage(ObjectError error) {
        return (error instanceof FieldError)
                ? ((FieldError) error).getField() + " " + error.getDefaultMessage()
                : error.getDefaultMessage();
    }
}
