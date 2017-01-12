package com.lhl.security20161216;

import com.lhl.security20161216.bean.User;
import com.lhl.security20161216.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by lunhengle on 2017/1/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext-base.xml")
public class TestUserDao {
    /**
     * 日志.
     */
    Logger logger = LoggerFactory.getLogger(TestUserDao.class);

    /**
     * 注入userDao.
     */
    @Autowired
    private UserDao userDao;

    @Test
    public void testUserByUsername() {
        User user = userDao.getUserByUsername("测试1");
        logger.info("id = " + user.getId() + " username = " + user.getUsername() + " password = " + user.getPassword());
    }
}
