package com.example.webflux.app.controller.handler;

import com.example.webflux.app.common.dto.ErrorDto;
import com.example.webflux.app.common.exception.CategoryDoesntExistException;
import com.example.webflux.app.common.exception.ProductAlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlerException {

    @ExceptionHandler(value = ProductAlreadyExistException.class)
    public ResponseEntity<ErrorDto> handlerProductAlreadyExistException(ProductAlreadyExistException exception) {

        HttpStatus httpStatusCode = HttpStatus.BAD_REQUEST;

        ErrorDto errorDto = ErrorDto.builder()
                .code(String.valueOf(httpStatusCode.value()))
                .message(exception.getMessage())
                .build();

        return ResponseEntity.status(httpStatusCode).body(errorDto);
    }

    @ExceptionHandler(value = CategoryDoesntExistException.class)
    public ResponseEntity<ErrorDto> handlerCategoryDoesntExistException(CategoryDoesntExistException exception) {

        HttpStatus httpStatusCode = HttpStatus.CONFLICT;

        ErrorDto errorDto = ErrorDto.builder()
                .code(String.valueOf(httpStatusCode.value()))
                .message(exception.getMessage())
                .build();

        return ResponseEntity.status(httpStatusCode).body(errorDto);
    }

}
