package com.org.lasantos.api.fast.exceptions;

import com.org.lasantos.api.fast.exceptions.customException.DtoValidationException;
import com.org.lasantos.api.fast.exceptions.customException.NotFoundCustomException;
import com.org.lasantos.api.fast.exceptions.customException.UniqueConstraintException;
import com.org.lasantos.api.fast.exceptions.errorResponse.ErrorForm;
import com.org.lasantos.api.fast.exceptions.errorResponse.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = {UniqueConstraintException.class})
    public ResponseEntity<?> uniqueConstraintException(UniqueConstraintException ex, WebRequest webRequest) {

        return new ResponseEntity(
                ErrorMessage.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .message(HttpStatus.BAD_REQUEST.name())
                        .description(ex.getMessage())
                        .build()
                , HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(value = {NotFoundCustomException.class})
    public ResponseEntity<?> notFoundCustomException(NotFoundCustomException ex, WebRequest webRequest) {

        return new ResponseEntity(
                ErrorMessage.builder()
                        .status(HttpStatus.NOT_FOUND.value())
                        .message(HttpStatus.NOT_FOUND.name())
                        .description(ex.getMessage())
                        .build()
                , HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(value = {DtoValidationException.class})
    public ResponseEntity<?> dtoValidationException(DtoValidationException ex, WebRequest webRequest) {

        return new ResponseEntity(
                new ErrorForm(HttpStatus.BAD_REQUEST.value(),
                        HttpStatus.BAD_REQUEST.name(),
                        ex.getMessage(),
                        ex.getErrorsForm()
                )
                , HttpStatus.BAD_REQUEST);
    }


}
