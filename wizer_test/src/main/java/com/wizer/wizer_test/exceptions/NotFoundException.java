package com.wizer.wizer_test.exceptions;

public class NotFoundException extends BaseException{
    public NotFoundException(String code, String message) {
        super(code, message);
    }
}
