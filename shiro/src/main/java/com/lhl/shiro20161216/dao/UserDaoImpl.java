package com.lhl.shiro20161216.dao;


import com.lhl.shiro20161216.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by lunhengle on 2017/1/11.
 */
@Repository
public class UserDaoImpl implements UserDao {
    /**
     * 注入jdbcTemplate.
     */
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 根据用户名获取用户.
     *
     * @param username 用户名
     * @return 用户
     */
    @Override
    public User getUserByUsername(String username) {
        final String sql = "SELECT ID,USERNAME,PASSWORD,ENABLED FROM USER WHERE USERNAME = ?";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, new Object[]{username});
        User user = null;
        if (list != null && list.size() > 0) {
            Map<String, Object> map = list.get(0);
            user = new User();
            user.setId((Integer) map.get("ID"));
            user.setUsername((String) map.get("USERNAME"));
            user.setPassword((String) map.get("PASSWORD"));
            user.setEnabled((Integer) map.get("ENABLED"));
        }
        return user;
    }

    /**
     * 根据用户名获取用户角色.
     *
     * @param username 用户名
     * @return 用户角色
     */
    @Override
    public List<String> getRolesByUsername(String username) {
        final String sql = "SELECT r.ROLE "
                + " FROM USER u,USER_ROLE ur,ROLE r "
                + " WHERE u.ID=ur.USER_ID "
                + " AND ur.ROLE_ID=r.ID "
                + " AND u.USERNAME =?";
        return jdbcTemplate.queryForList(sql, new Object[]{username}, String.class);
    }

    /**
     * 根据用户名获取用户资源.
     *
     * @param username 用户名
     * @return 用户资源
     */
    @Override
    public List<String> getResourcesByUsername(String username) {
        final String sql = "  SELECT re.URL "
                + " FROM USER u,USER_ROLE ur,ROLE r,ROLE_RESOURCE rr,RESOURCE re"
                + " WHERE re.ID=rr.RESOURCE_ID "
                + " AND rr.ROLE_ID=r.ID "
                + " AND r.ID=ur.ROLE_ID "
                + " AND ur.USER_ID=u.ID "
                + " AND u.USERNAME = ?  ";
        return jdbcTemplate.queryForList(sql, new Object[]{username}, String.class);
    }

    /**
     * 获取所有角色权限.
     *
     * @return 角色权限
     */
    @Override
    public List<Map<String, Object>> getRoleResources() {
        final String sql = "  SELECT r.ROLE role,re.URL url "
                + " FROM USER u,USER_ROLE ur,ROLE r,ROLE_RESOURCE rr,RESOURCE re"
                + " WHERE re.ID=rr.RESOURCE_ID "
                + " AND rr.ROLE_ID=r.ID "
                + " AND r.ID=ur.ROLE_ID "
                + " AND ur.USER_ID=u.ID ";
        return this.jdbcTemplate.queryForList(sql);
    }
}
