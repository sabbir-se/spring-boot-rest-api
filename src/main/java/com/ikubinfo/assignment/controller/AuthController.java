package com.ikubinfo.assignment.controller;

import com.ikubinfo.assignment.dto.CustomException;
import com.ikubinfo.assignment.dto.LoginDto;
import com.ikubinfo.assignment.service.AuthService;
import com.ikubinfo.assignment.util.APIConstant;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by sabbir on 9/29/21.
 */

@RestController
@RequestMapping("api/v1/auth")
@Api(description = "Rest API which provides a service for user auth")
public class AuthController {

    @Autowired
    HttpServletRequest request;

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public @ResponseBody String registration(@RequestBody LoginDto loginData) throws CustomException {
        return authService.createAuthUser(loginData);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody String login(@RequestBody LoginDto loginData) throws CustomException {
        return authService.login(loginData);
    }

    @RequestMapping(value = "/refresh/token", method = RequestMethod.GET)
    public @ResponseBody String refreshToken() throws CustomException {
        String userName = (String) request.getAttribute(APIConstant.USERNAME);
        return authService.refreshToken(userName);
    }
}
