package com.lhl.security20161216.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lunhengle on 2017/1/12.
 * 登陆成功.
 */
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    /**
     * 定义日志.
     */
    private static final Logger logger = LoggerFactory.getLogger(LoginSuccessHandler.class);

    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        logger.info(userDetails.getUsername() + ">>>>>>>>>>>>>>>>>>>登陆成功!");
        logger.info("登陆路径：" + this.getDefaultTargetUrl());
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
