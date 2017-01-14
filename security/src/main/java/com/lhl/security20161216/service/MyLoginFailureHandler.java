package com.lhl.security20161216.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lunhengle on 2017/1/12.
 * 登陆失败
 */
public class MyLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    private static final Logger logger = LoggerFactory.getLogger(MyLoginFailureHandler.class);

    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        logger.info("登陆失败，异常信息>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + exception.getLocalizedMessage());
        super.onAuthenticationFailure(request, response, exception);
    }
}
