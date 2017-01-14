package com.lhl.security20161216;

import com.lhl.security20161216.bean.Resource;
import com.lhl.security20161216.bean.Role;
import com.lhl.security20161216.bean.User;
import com.lhl.security20161216.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

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

    /**
     * 测试用户.
     */
    @Test
    public void testUserByUsername() {
        User user = userDao.getUserByUsername("user");
        logger.info("id = " + user.getId() + " username = " + user.getUsername() + " password = " + user.getPassword());
    }

    /**
     * 测试用户角色.
     */
    @Test
    public void testRolesByUsername() {
        List<Role> roleList = userDao.getRolesByUsername("user");
        for (Role role : roleList) {
            logger.info("id = " + role.getId() + " role  = " + role.getRole());
        }
    }

    /**
     * 测试用户资源.
     */
    @Test
    public void testResourcesByUsername() {
        List<Resource> resourceList = userDao.getResourcesByUsername("user");
        for (Resource resource : resourceList) {
            logger.info("id = " + resource.getId() + " url  = " + resource.getUrl());
        }
    }

    /**
     * 获取角色权限.
     */
    @Test
    public void testRoleResources() {
        List<Map<String, Object>> list = userDao.getRoleResources();
        for (Map map : list) {
            logger.info("role = " + map.get("ROLE") + " url = " + map.get("URL"));
        }
    }
}
