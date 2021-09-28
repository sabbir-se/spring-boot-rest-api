package com.ikubinfo.assignment.handler;

import com.ikubinfo.assignment.dto.CustomException;
import com.ikubinfo.assignment.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by sabbir on 9/29/21.
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<ErrorResponse> customException(CustomException customException) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(customException.getCode());
        errorResponse.setMessage(customException.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
