package com.ikubinfo.assignment.util;

import org.springframework.http.HttpStatus;

/**
 * Created by sabbir on 9/28/21.
 */
public class Constant {

    static final String MESSAGE = "message";

    public static final String CREATE_MESSAGE = "Create Successfully.";
    public static final String UPDATE_MESSAGE = "Update Successfully.";
    public static final String DELETE_MESSAGE = "Delete Successfully.";

    public static final int BAD_REQUEST = HttpStatus.BAD_REQUEST.value();
    public static final int NOT_FOUND_REQUEST = HttpStatus.NOT_FOUND.value();

    public static final String USER_EXIST = "User already exists!";
    public static final String USER_NOT_FOUND = "User not found!";
    public static final String USER_FNAME_NOT_FOUND = "First name must not be null!";
    public static final String USER_PHONE_NOT_FOUND = "Phone number must not be null!";
}
