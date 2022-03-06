package com.unitech.error;

import com.unitech.client.error.FastForestException;
import com.unitech.model.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonResponse> handleInternalServerErrors(Exception ex) {
        CommonResponse response = new CommonResponse(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
                ex.getMessage());
        log.error("Error unexpected exception:{}", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(FastForestException.class)
    public ResponseEntity<CommonResponse> handleClientErrors(FastForestException ex) {
        CommonResponse response = new CommonResponse(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
                ex.getErrorResponse().getError());
        log.error("Error unexpected exception:{}", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CommonException.class)
    public ResponseEntity<CommonResponse> handleCommonErrors(CommonException ex) {
        CommonResponse response = new CommonResponse(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
                ex.getMessage());
        log.error("Error unexpected exception:{}", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Set<String> errorMessages = new HashSet<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errorMessages.add(fieldName + " " + errorMessage);
            log.error("Validation exception fieldName: {} , message: {}", fieldName, errorMessage);
        });
        CommonResponse response = new CommonResponse(String.valueOf(status.value()),ex.getMessage());
        return new ResponseEntity<>(response, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("Http Message Not Readable Exception, message:{}", ex.getMessage());
        CommonResponse response = new CommonResponse(String.valueOf(status.value()),ex.getMessage());
        return new ResponseEntity<>(response, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("Method not supported,exception:{}", ex.getMessage());
        CommonResponse response = new CommonResponse(String.valueOf(status.value()),ex.getMessage());
        return new ResponseEntity<>(response, headers, HttpStatus.METHOD_NOT_ALLOWED);
    }
}
