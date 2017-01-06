package com.lhl.utils20161216.orm.mb;

import com.lhl.utils20161216.bean.mb.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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

    /**
     * 根据用户id更新用户名.
     *
     * @param username 用户名
     * @param id       用户id
     */
    void updateUserById(String username, int id);

    /**
     * 更新用户.
     *
     * @param user 用户
     */
    void updateUser(User user);

    /**
     * 更新用户list.
     *
     * @param userList 用户list
     */
    void updateUserList(List<User> userList);

    /**
     * 根据ID获取user列表.
     *
     * @param list 用户 id list
     * @return user列表
     */
    List<User> getUserInIds(List list);
}
