package com.lhl.security20161216.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户跳转.
 * Created by lunhengle on 2017/1/14.
 */
@Controller
public class UserController {
    /**
     * 用户信息.
     */
    @RequestMapping("/user.do")
    public String user() {
        return "user";
    }

    /**
     * 管理员信息.
     */
    @RequestMapping("/admin.do")
    public String admin() {
        return "admin/admin";
    }
}
