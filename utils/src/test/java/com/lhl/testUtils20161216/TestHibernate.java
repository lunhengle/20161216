package com.lhl.testUtils20161216;

import com.lhl.utils20161216.bean.hi.Page;
import com.lhl.utils20161216.bean.hi.User;
import com.lhl.utils20161216.orm.hi.UserDao;
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
 * Created by lunhengle on 2017/1/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring/applicationContext-hibernate.xml")
public class TestHibernate {
    private static Logger logger = LoggerFactory.getLogger(TestHibernate.class);
    @Autowired
    private UserDao userDaoHi;

    /**
     * 得到所有的用户.
     */
    @Test
    public void testGetListUser() {
        List<User> list = userDaoHi.getListUser();
        for (User user : list) {
            logger.info(" id = " + user.getId() + " username = " + user.getUsername() + " password = " + user.getPassword());
        }
    }

    /**
     * 根据用户名获取用户.
     */
    @Test
    public void testGetListUserByUsername() {
        List<User> list = userDaoHi.getListUserByUsername("测试1");
        for (User user : list) {
            logger.info(" id = " + user.getId() + " username = " + user.getUsername() + " password = " + user.getPassword());
        }
    }

    /**
     * 根据id和用户名获取用户.
     */
    @Test
    public void testGetListUserByIdAndUsername() {
        List<User> list = userDaoHi.getListUserByIdAndUsername(1, "测试1");
        for (User user : list) {
            logger.info("id = " + user.getId() + " username  = " + user.getUsername() + " password = " + user.getPassword());
        }
    }

    /**
     * 根据用户名模糊获取用户.
     */
    @Test
    public void testGetListUserByUsernameLike() {
        List<User> list = userDaoHi.getListUserByUsernameLike("测试");
        for (User user : list) {
            logger.info(" id = " + user.getId() + " username = " + user.getUsername() + " password = " + user.getPassword());
        }
    }

    /**
     * 根据id获取用户.
     */
    @Test
    public void testGetUser() {
        User user = userDaoHi.getUser(1);
        logger.info(" id = " + user.getId() + " username = " + user.getUsername() + " password = " + user.getPassword());
    }

    /**
     * 测试添加用户
     */
    @Test
    public void testAddUser() {
        User u = new User();
        u.setUsername("lunhengle");
        u.setPassword("123465");
        User user = userDaoHi.addUser(u);
        logger.info(" id = " + user.getId() + " username = " + user.getUsername() + " password = " + user.getPassword());
        user = userDaoHi.getUser(user.getId());
        logger.info(" id = " + user.getId() + " username = " + user.getUsername() + " password = " + user.getPassword());
    }

    /**
     * 更新用户.
     */
    @Test
    public void testUpdateUser() {
        User u = userDaoHi.getUser(1);
        logger.info("更新之前！");
        logger.info(" id = " + u.getId() + " username = " + u.getUsername() + " password = " + u.getPassword());
        u.setUsername("伦恒乐");
        User user = userDaoHi.updateUser(u);
        logger.info(" id = " + user.getId() + " username = " + user.getUsername() + " password = " + user.getPassword());
        user = userDaoHi.getUser(1);
        logger.info(" id = " + user.getId() + " username = " + user.getUsername() + " password = " + user.getPassword());
    }

    /**
     * 删除用户.
     */
    @Test
    public void testDeleteUser() {
        User user = userDaoHi.getUser(1);
        logger.info(" id = " + user.getId() + " username = " + user.getUsername() + " password = " + user.getPassword());
        userDaoHi.deleteUser(user);
        user = userDaoHi.getUser(1);
        Assert.assertNull(user);
    }

    /**
     * 得到总数.
     */
    @Test
    public void testCountUser() {
        long count = userDaoHi.countUser();
        logger.info(" count = " + count);
    }

    /**
     * 分页得到用户.
     */
    @Test
    public void testUserListPage() {
        String hql = " from User where username = ?";
        String[] params = new String[]{"测试1"};
        Page<User> page = userDaoHi.getUserListPage(hql, params, 1, 3);
        logger.info("当前多少页 count = " + page.getCurrentPage() + " 一共多少页 totalPage = " + page.getTotalPage() + " 一共多少条 totalCount = " + page.getTotalCount() + " 每页记录数 pageSize = " + page.getPageSize());
        for (User user : page.getList()) {
            logger.info(" id = " + user.getId() + " username = " + user.getUsername() + " password = " + user.getPassword());
        }
    }
}
