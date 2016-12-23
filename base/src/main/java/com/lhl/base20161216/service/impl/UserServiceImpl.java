package com.lhl.base20161216.service.impl;

import com.lhl.base20161216.bean.User;
import com.lhl.base20161216.service.UserService;
import org.springframework.stereotype.Service;

/**
 * Created by lunhengle on 2016/12/16.
 */
@Service
public class UserServiceImpl implements UserService {
    /**
     * 得到用户.
     *
     * @return 用户
     */
    public User getUser() {
        User user = new User();
        user.setId(1);
        user.setUsername("lun");
        user.setPassword("password");
        return user;
    }
}
