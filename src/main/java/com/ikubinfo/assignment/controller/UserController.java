package com.ikubinfo.assignment.controller;

import com.ikubinfo.assignment.dto.CustomException;
import com.ikubinfo.assignment.model.User;
import com.ikubinfo.assignment.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by sabbir on 9/29/21.
 */

@RestController
@RequestMapping("/api/v1/user")
@Api(description = "Rest API which provides a service for storing, updating, retrieving and deleting user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Add an user")
    @PostMapping("")
    public @ResponseBody
    String createUser(@RequestBody User user) throws CustomException {
        return userService.createUser(user);
    }

    @ApiOperation(value = "Update an user")
    @PutMapping("/{userID}")
    public @ResponseBody String updateUser(@PathVariable("userID") Long userID,
                                             @RequestBody User user) throws CustomException {
        user.setId(userID);
        return userService.updateUser(user);
    }

    @ApiOperation(value = "Delete an user")
    @DeleteMapping("/{userID}")
    public @ResponseBody String deleteUser(@PathVariable("userID") Long userID) throws CustomException {
        return userService.deleteUser(userID);
    }

    @ApiOperation(value = "Get an user by Id")
    @GetMapping("/{userID}")
    public ResponseEntity<User> getUser(@PathVariable("userID") Long userID) throws CustomException {
        return new ResponseEntity<>(userService.getUserByID(userID), HttpStatus.OK);
    }

    @ApiOperation(value = "View a list of users", response = ResponseEntity.class)
    @GetMapping("")
    public ResponseEntity<?> getAllUser() throws CustomException {
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }
}
