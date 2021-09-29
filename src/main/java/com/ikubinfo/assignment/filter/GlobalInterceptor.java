package com.ikubinfo.assignment.filter;

import com.ikubinfo.assignment.service.AuthService;
import com.ikubinfo.assignment.service.TokenService;
import com.ikubinfo.assignment.util.APIConstant;
import com.ikubinfo.assignment.util.Utils;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by sabbir on 9/29/21.
 */


public class GlobalInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(GlobalInterceptor.class.getName());

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthService authService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object o) throws Exception {

        logger.debug("------------------Pre handle----------------");
        String accessToken = request.getHeader(APIConstant.AUTHORIZATION);
        String path = request.getRequestURI();
        String method = request.getMethod();

        String finalPath;
        if(!Utils.isNullOrEmpty(request.getQueryString())){
            finalPath = path + "?" + request.getQueryString();
        } else {
            finalPath = path;
        }

        logger.info("Request path: " + finalPath);
        logger.info("Request method: " + method);

        if(method.equals(APIConstant.OPTIONS)){
            response.setStatus(HttpStatus.OK.value());
            return false;

        } else {
            if(path.contains(APIConstant.SWAGGER_URL)
                    || path.contains(APIConstant.ERROR_URL)
                    || path.contains(APIConstant.API_REGISTRATION)
                    || path.contains(APIConstant.API_LOGIN)){
                return true;

            } else {
                if (Utils.isNullOrEmpty(accessToken)) {
                    logger.info("AccessToken not defined.");
                    response.getWriter().write("AccessToken not defined.");
                    response.setStatus(HttpStatus.FORBIDDEN.value());
                    return false;

                } else {
                    String finalAccessToken = Utils.getFinalToken(accessToken);
                    logger.info("AccessToken found: " + finalAccessToken);

                    Claims tokenObj = tokenService.parseToken(finalAccessToken);
                    if (tokenObj == null) {
                        logger.info("AccessToken parse failed!");
                        response.getWriter().write("AccessToken parse failed.");
                        response.setStatus(HttpStatus.FORBIDDEN.value());
                        return false;
                    }

                    if (!authService.isUserSessionExist(tokenObj.getId(), finalAccessToken)) {
                        logger.info("UserSession don't exist by username: " + tokenObj.getId());
                        response.getWriter().write("UserSession don't exist, Please login again!");
                        response.setStatus(HttpStatus.FORBIDDEN.value());
                        return false;
                    }

                    request.setAttribute(APIConstant.ACCESS_TOKEN, finalAccessToken);
                    request.setAttribute(APIConstant.USERNAME, tokenObj.getId());
                }
            }
        }
        logger.info("Authorized url found.");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }
}
