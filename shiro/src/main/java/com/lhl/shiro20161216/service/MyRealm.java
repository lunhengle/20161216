package com.lhl.shiro20161216.service;

import com.lhl.shiro20161216.bean.User;
import com.lhl.shiro20161216.dao.UserDao;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * 定义shiro realm.
 * Created by lunhengle on 2017/1/18.
 */
@Service
public class MyRealm extends AuthorizingRealm {
    /**
     * 注入userDao.
     */
    @Autowired
    private UserDao userDao;

    /**
     * 授权.
     *
     * @param principalCollection
     * @return 授权信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        Set<String> setRoles = new HashSet<>(userDao.getRolesByUsername(username));
        Set<String> setResources = new HashSet<>(userDao.getResourcesByUsername(username));
        simpleAuthorizationInfo.setRoles(setRoles);
        simpleAuthorizationInfo.setStringPermissions(setResources);
        return simpleAuthorizationInfo;
    }

    /**
     * 认证，获取用户信息.
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        if (username == null) {
            throw new AccountException("用户名不能为空！");
        }
        User user = userDao.getUserByUsername(username);
        if (user == null) {
            throw new UnknownAccountException("用户不存在！");
        }
        if (user.getEnabled() == 0) {
            throw new DisabledAccountException("帐号已被禁用");
        }
        AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), "myRealm");
        return authenticationInfo;
    }
}
