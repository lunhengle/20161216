package com.lhl.shiro20161216.controller;

import com.lhl.shiro20161216.service.EncryptUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


/**
 * 登陆Controller.
 * Created by lunhengle on 2017/1/18.
 */
@Controller
public class LoginController {
    /**
     * 日志.
     */
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    /**
     * 跳转到登陆界面.
     *
     * @return 登陆界面
     */
    @RequestMapping(value = "/loginPage")
    public String loginPage() {
        return "login";
    }

    /**
     * 登陆成功.
     *
     * @return 主页面
     */
    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request, Model model) {
        String url = "login";
        String msg;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        password = EncryptUtil.encrypt(username, password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            //认证通过跳转到home 页面
            if (subject.isAuthenticated()) {
                url = "home";
            }
        } catch (IncorrectCredentialsException e) {
            msg = "登录密码错误. Password for account " + token.getPrincipal() + " was incorrect.";
            model.addAttribute("message", msg);
            logger.info(msg);
        } catch (ExcessiveAttemptsException e) {
            msg = "登录失败次数过多";
            model.addAttribute("message", msg);
            logger.info(msg);
        } catch (LockedAccountException e) {
            msg = "帐号已被锁定. The account for username " + token.getPrincipal() + " was locked.";
            model.addAttribute("message", msg);
            logger.info(msg);
        } catch (DisabledAccountException e) {
            msg = "帐号已被禁用. The account for username " + token.getPrincipal() + " was disabled.";
            model.addAttribute("message", msg);
            logger.info(msg);
        } catch (ExpiredCredentialsException e) {
            msg = "帐号已过期. the account for username " + token.getPrincipal() + "  was expired.";
            model.addAttribute("message", msg);
            logger.info(msg);
        } catch (UnknownAccountException e) {
            msg = "帐号不存在. There is no user with username of " + token.getPrincipal();
            model.addAttribute("message", msg);
            logger.info(msg);
        } catch (UnauthorizedException e) {
            msg = "您没有得到相应的授权！" + e.getMessage();
            model.addAttribute("message", msg);
            logger.info(msg);
        }
        return url;
    }

    /**
     * 退出系统.
     *
     * @return 登陆界面
     */
    @RequestMapping(value = "/logout")
    public String logout() {
        return "login";
    }
}
