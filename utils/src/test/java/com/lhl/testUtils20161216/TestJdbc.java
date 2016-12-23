package com.lhl.testUtils20161216;

import com.lhl.utils20161216.bean.User;
import com.lhl.utils20161216.orm.jdbc.UserDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by lunhengle on 2016/12/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-jdbc.xml"})
public class TestJdbc {
    private static Logger logger = LoggerFactory.getLogger(TestJdbc.class);
    @Autowired
    private UserDao userDao;

    /**
     * 得到单个用户.
     */
    @Test
    public void testGetUser() {
        User user = userDao.getUser(1);
        logger.info("id = " + user.getId() + " username = " + user.getUsername() + " password = " + user.getPassword());
    }

    /**
     * 获取单个用户
     */
    @Test
    public void testGetUserByIdAndUsername() {
        User user = userDao.getUserByIdAndUserName(1, "测试1");
        logger.info("id = " + user.getId() + " username = " + user.getUsername() + " password = " + user.getPassword());
    }

    /**
     * 获取用户.
     */
    @Test
    public void testGetUsers() {
        List<User> userList = userDao.getUserList();
        for (User user : userList) {
            logger.info("id = " + user.getId() + " username = " + user.getUsername() + " password = " + user.getPassword());
        }
    }

    /**
     * 添加用户.
     */
    @Test
    public void testAddUser() {
        User user = new User();
        user.setId(6);
        user.setUsername("测试6");
        user.setPassword("123456");
        userDao.addUser(user);
        User user1 = userDao.getUser(6);
        logger.info("id = " + user1.getId() + " username = " + user1.getUsername() + " password = " + user1.getPassword());
    }

    /**
     * 修改用户.
     */
    @Test
    public void testUpdateUser() {
        User user = userDao.getUser(1);
        logger.info("id = " + user.getId() + " username = " + user.getUsername() + " password = " + user.getPassword());
        user.setPassword("修改后");
        userDao.updateUser(user);
        User user1 = userDao.getUser(1);
        logger.info("id = " + user1.getId() + " username = " + user1.getUsername() + " password = " + user1.getPassword());
    }

    /**
     * 删除用户.
     */
    @Test
    public void testDeleteUser() {
        User user = userDao.getUser(1);
        logger.info("id = " + user.getId() + " username = " + user.getUsername() + " password = " + user.getPassword());
        userDao.deleteUser(1);
    }
}
