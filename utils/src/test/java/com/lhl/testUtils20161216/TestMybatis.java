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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 更新用户.
     */
    @Test
    public void testUpdateUserById() {
        userDaoMb.updateUserById("lunhengle", 1);
        User user = userDaoMb.getUserById(1);
        logger.info("id = " + user.getId() + " username = " + user.getUsername() + " password = " + user.getPassword());
    }

    /**
     * 更新用户对象.
     */
    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setId(1);
        user.setUsername("lunhengle");
        userDaoMb.updateUser(user);
        user = userDaoMb.getUserById(1);
        logger.info("id = " + user.getId() + " username = " + user.getUsername() + " password = " + user.getPassword());
    }

    /**
     * 批量更新用户对象.
     */
    @Test
    public void testUpdateUserList() {
        User user1 = new User();
        user1.setId(1);
        user1.setUsername("lunhengle");
        User user2 = new User();
        user2.setId(2);
        user2.setUsername("lunyu");
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        userDaoMb.updateUserList(userList);
        List list =new ArrayList();
        list.add(1);
        list.add(2);
        List<User> users = userDaoMb.getUserInIds(list);
        for (User user : users) {
            logger.info(" id = " + user.getId() + " username = " + user.getUsername() + " password = " + user.getPassword());
        }
    }
}
