package com.ikubinfo.assignment.util;

import org.springframework.http.HttpStatus;

/**
 * Created by sabbir on 9/28/21.
 */
public class Constant {

    public static final String MESSAGE = "message";
    public static final int TIME_INTERVAL = 60000 * 60 * 24; // 60 Minute or 1 Hour

    public static final String SALT = "87c63aae-917c-42ce-b4c7-8a4847db4133";
    public static final String SECRET_KEY = "MTUxMWFlMjctNWZjNS00YmVlLWJlZTMtYmRkNTY2ZWQyY2E3NDg2YTNlNDktNTA0MS00NWRjLTg3NDktNGU5NGJhY2IwN2M3";

    public static final String CREATE_MESSAGE = "Create Successfully.";
    public static final String UPDATE_MESSAGE = "Update Successfully.";
    public static final String DELETE_MESSAGE = "Delete Successfully.";
    public static final String LOGIN_MESSAGE = "Login Successfully.";

    public static final int BAD_REQUEST = HttpStatus.BAD_REQUEST.value();
    public static final int NOT_FOUND_REQUEST = HttpStatus.NOT_FOUND.value();

    public static final String INVALID_TOKEN = "Invalid token.";

    public static final String USER_EXIST = "User already exists!";
    public static final String USER_NOT_FOUND = "User not found!";
    public static final String USER_FNAME_NOT_FOUND = "First name must not be null!";
    public static final String USER_PHONE_NOT_FOUND = "Phone number must not be null!";

    public static final String AUTH_USER_EXIST = "Auth user already exist!";
    public static final String USERNAME_NOT_FOUND = "Username must not be null!";
    public static final String PASSWORD_NOT_FOUND = "Password must not be null!";
    public static final String USER_LOGIN_NOT_FOUND = "User login not found!";
}
