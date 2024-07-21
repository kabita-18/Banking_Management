package com.example.bankingapp.exception;

import java.time.LocalTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.bankingapp.service.AccountServiceException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public @ResponseBody ExceptionResponse handleResourceNotFound(final ResourceNotFoundException exception,
            final HttpServletRequest request) {

        return createExceptionResponse(exception, request);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody ExceptionResponse handleException(final Exception exception,
            final HttpServletRequest request) {

        exception.printStackTrace();  // Print stack trace for debugging

        return createExceptionResponse(exception, request);
    }

    @ExceptionHandler(AccountServiceException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody ExceptionResponse handleAccountServiceException(final AccountServiceException exception,
            final HttpServletRequest request) {

        return createExceptionResponse(exception, request);
    }

    private ExceptionResponse createExceptionResponse(final Exception exception, final HttpServletRequest request) {
        ExceptionResponse error = new ExceptionResponse();
        error.setErrorMessage(exception.getMessage());
        error.setTimeStamp(LocalTime.now().toString());
        error.setRequestedURI(request.getRequestURI());
        return error;
    }
}
