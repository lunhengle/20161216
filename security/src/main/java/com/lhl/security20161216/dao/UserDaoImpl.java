package com.lhl.security20161216.dao;

import com.lhl.security20161216.bean.Resource;
import com.lhl.security20161216.bean.Role;
import com.lhl.security20161216.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
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
        return jdbcTemplate.queryForObject("SELECT ID,USERNAME,PASSWORD,ENABLED FROM USER WHERE USERNAME = ?", new Object[]{username}, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setId(rs.getInt("ID"));
                user.setUsername(rs.getString("USERNAME"));
                user.setPassword(rs.getString("PASSWORD"));
                user.setEnabled(rs.getInt("ENABLED"));
                return user;
            }
        });
    }

    /**
     * 根据用户名获取用户角色.
     *
     * @param username 用户名
     * @return 用户角色
     */
    @Override
    public List<Role> getRolesByUsername(String username) {
        final String sql = "SELECT r.* "
                + " FROM USER u,USER_ROLE ur,ROLE r "
                + " WHERE u.ID=ur.USER_ID "
                + " AND ur.ROLE_ID=r.ID "
                + " AND u.USERNAME =?";
        return jdbcTemplate.query(sql, new Object[]{username}, new RowMapper<Role>() {
            @Override
            public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
                Role role = new Role();
                role.setId(rs.getInt("ID"));
                role.setRole(rs.getString("ROLE"));
                return role;
            }
        });
    }

    /**
     * 根据用户名获取用户资源.
     *
     * @param username 用户名
     * @return 用户资源
     */
    @Override
    public List<Resource> getResourcesByUsername(String username) {
        final String sql = "  SELECT re.* "
                + " FROM USER u,USER_ROLE ur,ROLE r,ROLE_RESOURCE rr,RESOURCE re"
                + " WHERE re.ID=rr.RESOURCE_ID "
                + " AND rr.ROLE_ID=r.ID "
                + " AND r.ID=ur.ROLE_ID "
                + " AND ur.USER_ID=u.ID "
                + " AND u.USERNAME = ?  ";
        return jdbcTemplate.query(sql, new Object[]{username}, new RowMapper<Resource>() {
            @Override
            public Resource mapRow(ResultSet rs, int rowNum) throws SQLException {
                Resource resource = new Resource();
                resource.setId(rs.getInt("ID"));
                resource.setUrl(rs.getString("URL"));
                return resource;
            }
        });
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
