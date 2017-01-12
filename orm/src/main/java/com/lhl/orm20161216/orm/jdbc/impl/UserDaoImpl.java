package com.lhl.orm20161216.orm.jdbc.impl;

import com.lhl.orm20161216.bean.jdbc.Page;
import com.lhl.orm20161216.bean.jdbc.User;
import com.lhl.orm20161216.orm.jdbc.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
     * 得到用户.
     *
     * @param id id
     * @return 用户
     */
    @Override
    public Map<String, Object> getUserMap(int id) {
        return jdbcTemplate.queryForMap("SELECT * FROM USER WHERE ID = ?", new Object[]{id});
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
     * 分页获取用户列表.
     *
     * @param count    当前多少页
     * @param pageSize 一页多少条
     * @return 用户列表
     */
    @Override
    public List<User> getUserListPage(int count, final int pageSize) {
        String sqlCount = "SELECT COUNT(1) FROM USER";
        int rowTotal = jdbcTemplate.queryForObject(sqlCount, Integer.class);
        final Page page = new Page(rowTotal, count, pageSize);
        return jdbcTemplate.query("SELECT * FROM USER ", new Object[]{}, new ResultSetExtractor<List<User>>() {
            @Override
            public List<User> extractData(ResultSet rs) throws SQLException, DataAccessException {
                int currentRow = 0;
                List<User> userList = new ArrayList<>();
                while (rs.next() && currentRow < page.getEndIndex()) {
                    if (currentRow >= page.getBeginIndex()) {
                        User user = new User();
                        user.setId(rs.getInt("ID"));
                        user.setUsername(rs.getString("USERNAME"));
                        user.setPassword(rs.getString("PASSWORD"));
                        userList.add(user);
                    }
                    currentRow++;
                }
                return userList;
            }
        });
    }

    /**
     * 获取user list.
     *
     * @param username 名字
     * @return 返回 user list
     */
    @Override
    public List<Map<String, Object>> getUsersList(final String username) {
        return jdbcTemplate.queryForList("SELECT * FROM USER WHERE USERNAME LIKE ?", new Object[]{"%" + username + "%"});
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
