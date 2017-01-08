package com.lhl.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by lunhengle on 2017/1/8.
 */
@Controller
public class LoginController {
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/login")
    public String loginPage() {
        return "/login";
    }

    @RequestMapping("/loginPage")
    public String login(@RequestParam(value = "error", required = false) String error) {
        if (error != null) {
            return "error";
        }
        return "login";
    }

    @RequestMapping(value = {"/home"}, method = RequestMethod.GET)
    public String welcome() {
        return "home";
    }

}
