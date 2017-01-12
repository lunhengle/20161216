package com.lhl.security20161216.service;

import com.lhl.security20161216.bean.User;
import com.lhl.security20161216.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by lunhengle on 2017/1/11.
 * 登陆校验
 */
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDao.getUserByUsername(s);
        final String username = user.getUsername();
        final String password = user.getPassword();
        final int enabled = user.getEnabled();
        final List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        GrantedAuthority grantedAuthority = new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return "ROLE_USER";
            }
        };
        grantedAuthorities.add(grantedAuthority);

        return new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return grantedAuthorities;
            }

            @Override
            public String getPassword() {
                return password;
            }

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
