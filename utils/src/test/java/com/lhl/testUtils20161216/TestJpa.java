package com.lhl.testUtils20161216;

import com.lhl.utils20161216.bean.jpa.User;
import com.lhl.utils20161216.orm.jpa.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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

    @Test
    public void testGetUsers() {
        List<User> userList = userDaoJpa.getUsers();
        for (User user : userList) {
            logger.info("id = " + user.getId() + " username = " + user.getUsername() + " password = " + user.getPassword());
        }
    }
}
