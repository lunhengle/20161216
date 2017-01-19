package com.lhl.shiro20161216.controller;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户界面.
 * Created by lunhengle on 2017/1/19.
 */
@Controller
public class UserController {

    /**
     * 跳转到普通用户.
     *
     * @return 普通用户路径
     */
    @RequestMapping("/user")
    @RequiresRoles(value = {"ROLE_USER", "ROLE_ADMIN"}, logical = Logical.OR)
    public String user() {
        return "user";
    }

    /**
     * 跳转到管理员用户.
     *
     * @return 管理员用户路径
     */
    @RequiresRoles("ROLE_ADMIN")
    @RequestMapping("/admin")
    public String admin() {
        return "admin";
    }
}
