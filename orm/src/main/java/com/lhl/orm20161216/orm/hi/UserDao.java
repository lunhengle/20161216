package com.lhl.orm20161216.orm.hi;

import com.lhl.orm20161216.bean.hi.Page;
import com.lhl.orm20161216.bean.hi.User;

import java.util.List;

/**
 * Created by lunhengle on 2017/1/3.
 */
public interface UserDao {
    /**
     * 获取用户list.
     *
     * @return 用户list
     */
    List<User> getListUser();

    /**
     * 根据用户名称获取用户.
     *
     * @param username 用户名称
     * @return 用户列表
     */
    List<User> getListUserByUsername(String username);

    /**
     * 根据用户id和用户名获取用户.
     *
     * @param id       id
     * @param username 用户名
     * @return 用户列表
     */
    List<User> getListUserByIdAndUsername(int id, String username);

    /**
     * 根据用户名获取用户.
     *
     * @param username 用户名
     * @return 用户
     */
    List<User> getListUserByUsernameLike(String username);

    /**
     * 根据id 获取用户.
     *
     * @param id id
     * @return 用户
     */
    User getUser(int id);

    /**
     * 添加用户.
     *
     * @param user 用户
     * @return 新添加的用户
     */
    User addUser(User user);

    /**
     * 更新用户.
     *
     * @param user 用户
     * @return 更新过后的用户
     */
    User updateUser(User user);

    /**
     * 删除用户.
     *
     * @param user 用户
     */
    void deleteUser(User user);

    /**
     * 得到用户数.
     *
     * @return 用户数
     */
    long countUser();

    /**
     * 分页得到用户.
     *
     * @param hql         hql 语句
     * @param params      参数值
     * @param currentPage 第几页
     * @param pageSize    多少条
     * @return 用户列表
     */
    Page<User> getUserListPage(String hql, Object[] params, int currentPage, int pageSize);
}
