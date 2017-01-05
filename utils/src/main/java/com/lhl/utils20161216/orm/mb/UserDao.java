package com.lhl.utils20161216.orm.mb;

import com.lhl.utils20161216.bean.mb.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lunhengle on 2017/1/5.
 * mybatis dao
 */
@Repository("userDaoMb")
public interface UserDao {
    /**
     * 得到所有用户列表.
     *
     * @return 用户列表
     */
    List<User> getUserList();

    /**
     * 根据用户id 获取用户.
     *
     * @param id 用户id
     * @return 用户
     */
    User getUserById(int id);

    /**
     * 根据用户id和用户名获取用户.
     *
     * @param id       id
     * @param username 用户名
     * @return 用户
     */
    User getUserByIdAndUsername(@Param("ID") int id, @Param("USERNAME") String username);
}
