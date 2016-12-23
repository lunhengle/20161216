package com.lhl.testBase20161216;

import com.lhl.base20161216.bean.User;
import com.lhl.base20161216.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by lunhengle on 2016/12/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/base/applicationContext-base.xml")
public class TestUserService {
    /**
     * 日志.
     */
    private static Logger logger = LoggerFactory.getLogger(TestUserService.class);
    /**
     * 注入用户service.
     */
    @Resource
    private UserService userService;

    /**
     * 测试用户.
     */
    @Test
    public void testUserService() {
        if (userService == null) {
            logger.error("userService is null");
        }
        User user = userService.getUser();
        if (user == null) {
            logger.error("user is null");
        }
        logger.debug("user.id = " + user.getId() + " user.username = " + user.getUsername() + " user.password = " + user.getPassword());
    }
}
