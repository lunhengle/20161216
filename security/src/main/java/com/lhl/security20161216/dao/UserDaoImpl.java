package com.lhl.security20161216.dao;

import com.lhl.security20161216.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

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
        return jdbcTemplate.queryForObject("SELECT * FROM USER WHERE USERNAME = ?", new Object[]{username}, new RowMapper<User>() {
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
     * 根据id 获取用户.
     *
     * @param id 用户id
     * @return 用户
     */
    @Override
    public User getUserById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM USER WHERE PASSWORD = ?", new Object[]{"123456"}, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setId(rs.getInt("ID"));
                user.setUsername(rs.getString("USERNAME"));
                user.setPassword(rs.getString("PASSWORD"));
                return user;
            }
        });
    }
}
