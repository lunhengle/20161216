package com.lhl.shiro20161216.dao;


import com.lhl.shiro20161216.bean.Resource;
import com.lhl.shiro20161216.bean.Role;
import com.lhl.shiro20161216.bean.User;

import java.util.List;
import java.util.Map;

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
     * 根据用户名获取用户角色.
     *
     * @param username 用户名
     * @return 用户角色
     */
    List<String> getRolesByUsername(String username);

    /**
     * 根据用户名获取用户资源.
     *
     * @param username 用户名
     * @return 用户资源
     */
    List<String> getResourcesByUsername(String username);

    /**
     * 获取所有角色权限.
     *
     * @return 角色权限
     */
    List<Map<String, Object>> getRoleResources();


}
