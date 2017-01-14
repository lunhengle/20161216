package com.lhl.security20161216.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 错误信息.
 * Created by lunhengle on 2017/1/14.
 */
@Controller
public class ErrorController {
    private static Logger logger = LoggerFactory.getLogger(ErrorController.class);

    /**
     * 403 错误.
     *
     * @return 403 错误页面
     */
    @RequestMapping("/403")
    public String error403() {
        logger.info("403 error!");
        return "common/403";
    }

    /**
     * 404错误.
     *
     * @return 404 错误页面
     */
    @RequestMapping("/404")
    public String error404() {
        logger.info("404 error!");
        return "common/404";
    }

    /**
     * 500错误.
     *
     * @return 500错误页面
     */
    @RequestMapping("/500")
    public String error500() {
        logger.info("500 error!");
        return "common/500";
    }

}
