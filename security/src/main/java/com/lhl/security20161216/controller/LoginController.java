package com.lhl.security20161216.controller;

import com.lhl.security20161216.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by lunhengle on 2017/1/8.
 */
@Controller
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private UserDao userDao;

    /**
     * 登陆操作.
     *
     * @param error 错误参数
     * @return 登陆几面
     */
    @RequestMapping("/loginPage.do")
    public String login(@RequestParam(value = "error", required = false) String error) {
        logger.info("登陆操作!");
        if (error != null) {
            return "login";
        }
        return "login";
    }

    /**
     * 登陆.
     *
     * @return
     */
    @RequestMapping("/homePage.do")
    public String login() {
        logger.info("首页!");
        return "home";
    }
}
