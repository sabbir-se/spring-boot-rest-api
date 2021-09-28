package com.ikubinfo.assignment.dto;

/**
 * Created by sabbir on 9/28/21.
 */
public class CustomException extends Exception {

    private int code;
    private String message;

    public CustomException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
