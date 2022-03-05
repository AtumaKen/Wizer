package com.wizer.wizer_test.exceptions;

public class BaseException extends RuntimeException {

    public BaseException(String code, String message) {

        super(message);
        this.setCode(code);
    }


    private String code;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
