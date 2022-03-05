package com.wizer.wizer_test.exceptions;

import com.wizer.wizer_test.models.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return BaseResponse.builder().data(errors).code("400").description("Validation error").build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotFoundException.class)
    public BaseResponse handleNotfoundExceptions(
            NotFoundException ex) {


        return BaseResponse.builder().code(ex.getCode()).description(ex.getMessage()).build();
    }

    //MissingServletRequestParameterException

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public BaseResponse handleRequestParameterExceptions(
            MissingServletRequestParameterException ex) {

        Map<String, String> errors = new HashMap<>();
        errors.put(ex.getParameterName(), ex.getParameterType());
        return BaseResponse.builder().code("400")
                .description(ex.getMessage()).data(errors).build();
    }
}
