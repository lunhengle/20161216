package com.lhl.testUtils20161216;

import com.lhl.utils20161216.bean.mb.User;
import com.lhl.utils20161216.orm.mb.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by lunhengle on 2017/1/5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext-mybatis.xml")
public class TestMybatis {
    private static Logger logger = LoggerFactory.getLogger(TestMybatis.class);
    @Autowired
    private UserDao userDaoMb;

    /**
     * 得到所有的用户.
     */
    @Test
    public void testGetUserList() {
        List<User> userList = userDaoMb.getUserList();
        for (User user : userList) {
            logger.info(" id = " + user.getId() + " username = " + user.getUsername() + " password = " + user.getPassword());
        }
    }

    /**
     * 根据用户id获取用户.
     */
    @Test
    public void testGetUserById() {
        User user = userDaoMb.getUserById(1);
        logger.info("id = " + user.getId() + " username = " + user.getUsername() + " password = " + user.getPassword());
    }

    /**
     * 根据用户id和用户名获取用户
     */
    @Test
    public void testGetUserByIdAndUsername() {
        User user = userDaoMb.getUserByIdAndUsername(1, "测试1");
        logger.info("id = " + user.getId() + " username = " + user.getUsername() + " password = " + user.getPassword());
    }
}
