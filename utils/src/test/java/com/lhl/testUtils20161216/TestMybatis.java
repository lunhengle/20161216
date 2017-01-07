package com.lhl.testUtils20161216;

import com.lhl.utils20161216.bean.mb.User;
import com.lhl.utils20161216.orm.mb.UserDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
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

    @Test
    public void testGetUserInIds() {
        List list = new ArrayList();
        list.add(1);
        list.add(2);
        List<User> users = userDaoMb.getUserInIds(list);
        for (User user : users) {
            logger.info(" id = " + user.getId() + " username = " + user.getUsername() + " password = " + user.getPassword());
        }
    }

    /**
     * 批量更新用户对象.
     */
    @Test
    public void testUpdateUserList() {
        User user1 = new User();
        user1.setId(1);
        user1.setUsername("lunhengle");
        user1.setPassword("22222");
        User user2 = new User();
        user2.setId(2);
        user2.setUsername("lunyu");
        user2.setPassword("3333333");
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        userDaoMb.updateUserList(userList);
        List<User> users = userDaoMb.getUserList();
        for (User user : users) {
            logger.info(" id = " + user.getId() + " username = " + user.getUsername() + " password = " + user.getPassword());
        }
    }

    /**
     * 测试插入数据.
     */
    @Test
    public void testInsertUser() {
        User user = new User();
        user.setUsername("lunhengle");
        user.setPassword("123456");
        userDaoMb.insertUser(user);
        user = userDaoMb.getUserById(6);
        logger.info("id = " + user.getId() + " username = " + user.getUsername() + " password = " + user.getPassword());
    }

    /**
     * 测试批量插入数据.
     */
    @Test
    public void testInsertUserList() {
        List<User> userList = new ArrayList<>();
        User user1 = new User();
        user1.setUsername("lunhengle");
        user1.setPassword("123456");
        userList.add(user1);
        User user2 = new User();
        user2.setUsername("lunyu");
        user2.setPassword("123465");
        userList.add(user2);
        userDaoMb.insertUserList(userList);
        List list = new ArrayList();
        list.add(6);
        list.add(7);
        List<User> users = userDaoMb.getUserInIds(list);
        for (User user : users) {
            logger.info(" id = " + user.getId() + " username = " + user.getUsername() + " password = " + user.getPassword());
        }
    }

    /**
     * 删除用户.
     */
    @Test
    public void testDeleteUserById() {
        userDaoMb.deleteUserById(1);
        User user = userDaoMb.getUserById(1);
        Assert.assertNull(user);
    }

    /**
     * 批量删除用户.
     */
    @Test
    public void testDeleteUserList() {
        List list = new ArrayList();
        list.add(1);
        list.add(2);
        userDaoMb.deleteUserList(list);
        List<User> userList = userDaoMb.getUserInIds(list);
        Assert.assertEquals(0,userList.size());
    }
}
