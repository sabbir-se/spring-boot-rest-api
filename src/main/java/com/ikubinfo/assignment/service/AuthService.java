package com.ikubinfo.assignment.service;

import com.google.gson.JsonObject;
import com.ikubinfo.assignment.dto.CustomException;
import com.ikubinfo.assignment.dto.LoginDto;
import com.ikubinfo.assignment.model.UserLogin;
import com.ikubinfo.assignment.repository.UserLoginRepository;
import com.ikubinfo.assignment.util.Constant;
import com.ikubinfo.assignment.util.PasswordHash;
import com.ikubinfo.assignment.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sabbir on 9/29/21.
 */

@Service
public class AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class.getName());

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserLoginRepository userLoginRepository;

    public String createAuthUser(LoginDto userLogin) throws CustomException {
        logger.info("Create Auth User :: Start");
        validateUserLogin(userLogin);

        UserLogin existLogin = userLoginRepository.findUserLoginByUsername(userLogin.getUsername());
        if (null != existLogin) {
            logger.info("Auth user already exists.");
            return Utils.messageResponse(Constant.AUTH_USER_EXIST);
        }
        UserLogin newUserLogin = new UserLogin();
        newUserLogin.setUsername(userLogin.getUsername());

        logger.info("Password hashing....");
        newUserLogin.setPassword(PasswordHash.hash(userLogin.getPassword(), Constant.SALT));
        String accessToken = tokenService.createToken(String.valueOf(userLogin.getUsername()),
                Constant.TIME_INTERVAL);
        logger.info("Generated New Access Token: " + accessToken);
        newUserLogin.setAccessToken(accessToken);

        userLoginRepository.save(newUserLogin);
        logger.info("Auth user created successfully!");
        logger.info("Create Auth User :: End");

        JsonObject responseObj = new JsonObject();
        responseObj.addProperty("AccessToken", accessToken);
        responseObj.addProperty(Constant.MESSAGE, Constant.CREATE_MESSAGE);
        return responseObj.toString();
    }

    private void validateUserLogin(LoginDto userLogin) throws CustomException {
        if (Utils.isNullOrEmpty(userLogin.getUsername())) {
            logger.info("Username must not be null.");
            throw new CustomException(Constant.BAD_REQUEST, Constant.USERNAME_NOT_FOUND);
        }
        if (Utils.isNullOrEmpty(userLogin.getPassword())) {
            logger.info("Password must not be null.");
            throw new CustomException(Constant.BAD_REQUEST, Constant.PASSWORD_NOT_FOUND);
        }
    }

    public String login(LoginDto loginData) throws CustomException {
        logger.info("User trying to login:: Start");
        validateUserLogin(loginData);

        UserLogin userLogin = userLoginRepository.findUserLoginByUsernameAndPassword(loginData.getUsername(),
                PasswordHash.hash(loginData.getPassword(), Constant.SALT));
        if (null == userLogin) {
            logger.info("User login not found.");
            throw new CustomException(Constant.NOT_FOUND_REQUEST, Constant.USER_LOGIN_NOT_FOUND);
        }
        String accessToken = tokenService.createToken(String.valueOf(userLogin.getUsername()),
                Constant.TIME_INTERVAL);
        logger.info("Generated Access Token: " + accessToken);
        userLogin.setAccessToken(accessToken);
        userLoginRepository.save(userLogin);
        logger.info("User trying to login:: End");

        JsonObject responseObj = new JsonObject();
        responseObj.addProperty("AccessToken", accessToken);
        responseObj.addProperty(Constant.MESSAGE, Constant.LOGIN_MESSAGE);
        return responseObj.toString();
    }

    public String refreshToken(String userName) throws CustomException {
        logger.info("Get refresh token:: Start");
        UserLogin userLogin = userLoginRepository.findUserLoginByUsername(userName);
        if (null == userLogin) {
            logger.info("User login not found.");
            throw new CustomException(Constant.BAD_REQUEST, Constant.USER_LOGIN_NOT_FOUND);
        }
        String accessToken = tokenService.createToken(String.valueOf(userLogin.getUsername()),
                Constant.TIME_INTERVAL);
        logger.info("Generated New Access Token: " + accessToken);
        userLogin.setAccessToken(accessToken);
        userLoginRepository.save(userLogin);

        JsonObject refreshTokenResponse = new JsonObject();
        refreshTokenResponse.addProperty("AccessToken", accessToken);
        logger.info("Get refresh token:: End");
        return refreshTokenResponse.toString();
    }

    public Boolean isUserSessionExist(String userName, String accessToken) {
        logger.info("User session exist checking:: Start");
        UserLogin userLogin = userLoginRepository.findUserLoginByAccessTokenAndUsername(accessToken, userName);
        logger.info("User session exist checking:: End");
        return null != userLogin;
    }
}
