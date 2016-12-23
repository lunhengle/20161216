package com.lhl.utils20161216.orm.jdbc;

import com.lhl.utils20161216.bean.User;

import java.util.List;

/**
 * Created by lunhengle on 2016/12/23.
 * 用户dao.
 */
public interface UserDao {
    /**
     * 得到用户.
     *
     * @param id id
     * @return 用户
     */
    User getUser(int id);

    /**
     * 根据用户id和名字获取用户.
     *
     * @param id       id
     * @param username 用户名
     * @return 用户
     */
    User getUserByIdAndUserName(int id, String username);

    /**
     * 获取用户列表.
     *
     * @return 用户列表
     */
    List<User> getUserList();

    /**
     * 更新用户.
     *
     * @param user 用户
     */
    void updateUser(User user);

    /**
     * 添加用户.
     *
     * @param user 用户
     */
    void addUser(User user);

    /**
     * 删除用户.
     *
     * @param id 用户id
     */
    void deleteUser(int id);

}
