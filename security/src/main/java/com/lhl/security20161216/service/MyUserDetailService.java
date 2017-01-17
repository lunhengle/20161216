package com.lhl.security20161216.service;

import com.lhl.security20161216.bean.Role;
import com.lhl.security20161216.bean.User;
import com.lhl.security20161216.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 登陆校验
 * Created by lunhengle on 2017/1/11.
 */
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDao.getUserByUsername(s);
        if (user == null) {
            throw new AuthenticationServiceException("当前账号不存在！");
        }
        final String username = user.getUsername();
        final String password = user.getPassword();
        final int enabled = user.getEnabled();
        List<Role> roleList = userDao.getRolesByUsername(s);
        if (roleList == null || roleList.size() == 0) {
            throw new AuthenticationServiceException("未给当前账号指定角色！");
        }
        /**
         * 用户的权限列表.
         */
        final List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (final Role role : roleList) {
            GrantedAuthority grantedAuthority = new GrantedAuthority() {
                @Override
                public String getAuthority() {
                    return role.getRole();
                }
            };
            grantedAuthorities.add(grantedAuthority);
        }

        return new UserDetails() {
            /**
             * 当前用户所有的权限列表.
             * @return 用户权限列表
             */
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return grantedAuthorities;
            }

            /**
             * 密码.
             * @return 密码
             */
            @Override
            public String getPassword() {
                return password;
            }

            /**
             * 账号.
             * @return 账号
             */
            @Override
            public String getUsername() {
                return username;
            }

            /**
             * 是否过期.
             * @return true 不过期 false 过期
             */
            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            /**
             * 是否锁住.
             * @return true 没有锁住 false 锁住
             */
            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            /**
             * 凭证是否过期.
             * @return true 不过期 false 过期
             */
            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            /**
             * 是否有效.
             * @return true 有效 false 无效
             */
            @Override
            public boolean isEnabled() {
                return enabled == 1 ? true : false;
            }
        };
    }
}
