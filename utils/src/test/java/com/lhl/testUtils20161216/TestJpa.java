package com.lhl.testUtils20161216;

import com.lhl.utils20161216.bean.jpa.User;
import com.lhl.utils20161216.orm.jpa.UserDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lunhengle on 2016/12/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-jpa.xml"})
public class TestJpa {
    private static Logger logger = LoggerFactory.getLogger(TestJpa.class);
    @Autowired
    private UserDao userDaoJpa;

    /**
     * 获取全部用户.
     */
    @Test
    public void testGetListUser() {
        List<User> userList = userDaoJpa.getListUser();
        for (User user : userList) {
            logger.info("id = " + user.getId() + " username = " + user.getUsername() + " password = " + user.getPassword());
        }
    }

    /**
     * 获取用户.
     */
    @Test
    public void testGetUser() {
        User user = userDaoJpa.getUser(1);
        logger.info(" id = " + user.getId() + " username = " + user.getUsername() + " password = " + user.getPassword());
    }

    /**
     * 根据用户id和用户名获取用户.
     */
    @Test
    public void testGetUserByIdAndUsername() {
        User user = userDaoJpa.getUserByIdAndUsername(1, "测试1");
        logger.info(" id = " + user.getId() + " username = " + user.getUsername() + " password = " + user.getPassword());
    }

    /**
     * 根据用户名模糊查询.
     */
    @Test
    public void testGetUserLikeUsername() {
        List<User> list = userDaoJpa.getUserLikeUsername("测试");
        for (User user : list) {
            logger.info(" id = " + user.getId() + " username = " + user.getUsername() + " password = " + user.getPassword());
        }
    }

    /**
     * 得到用户汇总.
     */
    @Test
    public void testGetUserCount() {
        int count = userDaoJpa.getUserCount();
        logger.info(" count = " + count);
    }

    /**
     * 更新用户.
     */
    @Test
    public void updateUser() {
        User user = userDaoJpa.getUser(1);
        logger.info(" id = " + user.getId() + " username = " + user.getUsername() + " password = " + user.getPassword());
        userDaoJpa.updateUser("lunhengle", 1);
        user = userDaoJpa.getUser(1);
        logger.info(" id = " + user.getId() + " username = " + user.getUsername() + " password  = " + user.getPassword());
    }

    /**
     * 删除用户.
     */
    @Test
    public void deleteUser() {
        User user = userDaoJpa.getUser(1);
        logger.info(" id = " + user.getId() + " username = " + user.getUsername() + " password = " + user.getPassword());
        userDaoJpa.deleteUser(1);
        user = userDaoJpa.getUser(1);
        Assert.assertNull(user);
    }

    /**
     * 删除用户.
     */
    @Test
    public void deteleUser1() {
        User user = userDaoJpa.getUser(1);
        logger.info(" id = " + user.getId() + " username = " + user.getUsername() + " password = " + user.getPassword());
        userDaoJpa.delete(1);
        user = userDaoJpa.getUser(1);
        Assert.assertNull(user);
    }

    /**
     * 更新用户.
     */
    @Test
    public void updateUser1() {
        User user = userDaoJpa.getUser(1);
        user.setUsername("lunhengle");
        userDaoJpa.saveAndFlush(user);
        user = userDaoJpa.getUser(1);
        Assert.assertEquals(1, user.getId());
        Assert.assertEquals("lunhengle", user.getUsername());
        Assert.assertEquals("123456", user.getPassword());
    }

    /**
     * 保存用户.
     */
    @Test
    public void saveUser() {
        User u = new User();
        u.setUsername("lunhengle");
        u.setPassword("456789");
        u = userDaoJpa.save(u);
        logger.info(" id = " + u.getId() + " username = " + u.getUsername() + " password = " + u.getPassword());
        User user = userDaoJpa.getUser(6);
        logger.info(" id = " + user.getId() + " username = " + user.getUsername() + " password = " + user.getPassword());
    }

    /**
     * 批量保存.
     */
    @Test
    public void saveListUser() {
        List<User> userList = new ArrayList();
        User u1 = new User();
        u1.setUsername("add1");
        u1.setPassword("123456");
        userList.add(u1);
        User u2 = new User();
        u2.setUsername("add2");
        u2.setPassword("123456");
        userList.add(u2);
        userDaoJpa.save(userList);
        List<User> users = userDaoJpa.getUserLikeUsername("add");
        for (User user : users) {
            logger.info(" id = " + user.getId() + " username = " + user.getUsername() + " password = " + user.getPassword());
        }
    }

    /**
     * 批量更新.
     */
    @Test
    public void updateListUser() {
        List<User> users = new ArrayList<>();
        List<User> userList = userDaoJpa.getListUser();
        for (User user : userList) {
            user.setUsername("update");
            users.add(user);
        }
        userDaoJpa.save(users);
        List<User> userList1 = userDaoJpa.getUserLikeUsername("update");
        for (User user : userList1) {
            logger.info("id = " + user.getId() + " username = " + user.getUsername() + " password = " + user.getPassword());
        }
        Assert.assertEquals(userList.size(), userList1.size());
    }

    /**
     * 批量删除.
     */
    @Test
    public void deleteListUser() {
        List<User> userList = userDaoJpa.getListUser();
        userDaoJpa.delete(userList);
        userList = userDaoJpa.getListUser();
        Assert.assertEquals(0, userList.size());
    }

    /**
     * 分页.
     */
    @Test
    public void getUserListPage() {
        PageRequest pageRequest = new PageRequest(0, 3);
        Page<User> userPage = userDaoJpa.getUserListPage("测试", pageRequest);
        logger.info("totalElements(一共多少条) = " + userPage.getTotalElements() + " totalPages(一共多少页) = " + userPage.getTotalPages());
        for (User user : userPage) {
            logger.info(" id = " + user.getId() + " username = " + user.getUsername() + " password = " + user.getPassword());
        }
    }

    /**
     * 分页加排序.
     */
    @Test
    public void getUserListPageSort() {
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC, "id"));
        orders.add(new Sort.Order(Sort.Direction.ASC, "username"));
        Sort sort = new Sort(orders);
        PageRequest pageRequest = new PageRequest(0, 3, sort);
        Page<User> userPage = userDaoJpa.getUserListPage("测试", pageRequest);
        logger.info("totalElements(一共多少条) = " + userPage.getTotalElements() + "   totalPages(一共多少页) = " + userPage.getTotalPages());
        for (User user : userPage) {
            logger.info(" id = " + user.getId() + " username = " + user.getUsername() + " password = " + user.getPassword());
        }
    }
}
