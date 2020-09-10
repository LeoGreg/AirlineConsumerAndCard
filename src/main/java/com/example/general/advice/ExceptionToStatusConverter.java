package com.example.general.advice;

import com.example.general.util.exception.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Log4j2
@ControllerAdvice
public class ExceptionToStatusConverter {


    @ResponseBody
    @ExceptionHandler(DuplicateException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String duplicate(DuplicateException e) {
        log.info(">>>" + e.getMessage() + ":");
        return e.getMessage();
    }


    @ResponseBody
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    String duplicate(UnauthorizedException e) {
        log.info(">>>" + e.getMessage() + ":");
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(UnverifiedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    String duplicate(UnverifiedException e) {
        log.info(">>>" + e.getMessage() + ":");
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    String duplicate(AccessDeniedException e) {
        log.info(">>>" + e.getMessage() + ":");
        return e.getMessage();
    }


    @ResponseBody
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String duplicate(NotFoundException e) {
        log.info(">>>" + e.getMessage() + ":");
        return e.getMessage();
    }


    @ResponseBody
    @ExceptionHandler(NoEnoughBalanceToFulfillException.class)
    @ResponseStatus(HttpStatus.PAYMENT_REQUIRED)
    String pay(NoEnoughBalanceToFulfillException e) {
        log.info(">>>" + e + ":");
        return e.getMessage();
    }
}