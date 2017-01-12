package com.lhl.security20161216.dao;

import com.lhl.security20161216.bean.User;

/**
 * Created by lunhengle on 2017/1/11.
 */
public interface UserDao {
    /**
     * 根据用户名获取用户.
     *
     * @param username 用户名
     * @return 用户
     */
    User getUserByUsername(String username);

    /**
     * 根据id 获取用户.
     *
     * @param id 用户id
     * @return 用户
     */
    User getUserById(int id);

}
