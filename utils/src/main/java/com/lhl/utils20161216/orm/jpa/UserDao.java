package com.lhl.utils20161216.orm.jpa;

import com.lhl.utils20161216.bean.jpa.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lunhengle on 2016/12/27.
 */
@Repository("userDaoJpa")
public interface UserDao extends JpaRepository<User, Integer> {
    /**
     * 获取所有用户.
     *
     * @return 所有用户
     */
    @Query("SELECT u FROM User u")
    List<User> getListUser();

    /**
     * 根据id获取用户.
     *
     * @param id id
     * @return 用户
     */
    @Query("SELECT u FROM User u WHERE u.id = :id")
    User getUser(@Param("id") int id);

    /**
     * 根据id和用户名获取id.
     *
     * @param id       id
     * @param username 用户名
     * @return 用户
     */
    @Query("SELECT u FROM User u WHERE u.id = :id AND u.username = :username")
    User getUserByIdAndUsername(@Param("id") int id, @Param("username") String username);

    /**
     * 根据用户名模糊查询.
     *
     * @param username 用户名
     * @return 用户
     */
    @Query("SELECT u FROM User u WHERE u.username like  %:username%")
    List<User> getUserLikeUsername(@Param("username") String username);

    /**
     * 用户汇总.
     *
     * @return 用户
     */
    @Query("SELECT COUNT (u) FROM User u ")
    int getUserCount();

    /**
     * 根据id更新用户名.
     *
     * @param username 用户名
     * @param id       id
     */
    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.username=:username WHERE u.id=:id")
    void updateUser(@Param("username") String username, @Param("id") int id);

    /**
     * 删除用户.
     *
     * @param id id
     */
    @Transactional
    @Modifying
    @Query("DELETE FROM User u WHERE u.id =:id")
    void deleteUser(@Param("id") int id);

    /**
     * 分页查询用户信息.
     *
     * @param username 用户名称
     * @param pageable 分页
     * @return 用户信息
     */
    @Query("SELECT u FROM User u WHERE u.username like %:username%")
    Page<User> getUserListPage(@Param("username") String username, Pageable pageable);

}
