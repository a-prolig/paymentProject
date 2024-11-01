package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.custom.ErrorResponse;
import org.example.exception.IntegrationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ControllerAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse handleGenericException(IntegrationException ex) {
        System.out.println("handleGenericException ....");
        return getExceptionResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
    }

    private ErrorResponse getExceptionResponse(final String message, final Integer errorCode) {
        final ErrorResponse exceptionResponse = new ErrorResponse();
        exceptionResponse.setErrorCode(errorCode.toString());
        exceptionResponse.setErrorDescription(message);
        log.error("message:{}", exceptionResponse);
        return exceptionResponse;
    }
}
