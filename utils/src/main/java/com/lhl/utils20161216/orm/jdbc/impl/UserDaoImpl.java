package com.lhl.utils20161216.orm.jdbc.impl;

import com.lhl.utils20161216.bean.User;
import com.lhl.utils20161216.orm.jdbc.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by lunhengle on 2016/12/23.
 * 用户
 */
@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 得到用户.
     *
     * @param id id
     * @return 用户
     */
    @Override
    public User getUser(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM USER WHERE ID = ?", new Object[]{id}, new RowMapper<User>() {
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

    /**
     * 根据用户id和名字获取用户.
     *
     * @param id       id
     * @param username 用户名
     * @return 用户
     */
    @Override
    public User getUserByIdAndUserName(int id, String username) {
        return jdbcTemplate.queryForObject("SELECT * FROM USER WHERE ID = ? AND USERNAME = ?", new Object[]{id, username}, new RowMapper<User>() {
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

    /**
     * 获取用户列表.
     *
     * @return 用户列表
     */
    @Override
    public List<User> getUserList() {
        return jdbcTemplate.query("SELECT * FROM USER ", new Object[]{}, new RowMapper<User>() {
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

    /**
     * 更新用户.
     *
     * @param user 用户
     */
    @Override
    public void updateUser(User user) {
        jdbcTemplate.update("UPDATE USER SET PASSWORD = ? WHERE ID = ?", new Object[]{user.getPassword(), user.getId()});
    }

    /**
     * 添加用户.
     *
     * @param user 用户
     */
    @Override
    public void addUser(User user) {
        jdbcTemplate.update("INSERT INTO USER (ID,USERNAME,PASSWORD) VALUES (?,?,?)", new Object[]{user.getId(), user.getUsername(), user.getPassword()});
    }

    /**
     * 删除用户.
     *
     * @param id 用户id
     */
    @Override
    public void deleteUser(int id) {
        jdbcTemplate.update("DELETE FROM USER WHERE ID= ?", new Object[]{id});
    }
}
