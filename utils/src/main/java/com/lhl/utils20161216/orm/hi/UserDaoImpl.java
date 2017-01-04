package com.lhl.utils20161216.orm.hi;

import com.lhl.utils20161216.bean.hi.Page;
import com.lhl.utils20161216.bean.hi.PageTemplate;
import com.lhl.utils20161216.bean.hi.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lunhengle on 2017/1/3.
 */
@Repository("userDaoHi")
public class UserDaoImpl implements UserDao {
    @Autowired
    private HibernateTemplate hibernateTemplate;
    @Autowired
    private PageTemplate pageTemplate;


    /**
     * 获取用户list.
     *
     * @return 用户list
     */
    @Override
    public List<User> getListUser() {
        return hibernateTemplate.loadAll(User.class);
    }

    /**
     * 根据用户名称获取用户.
     *
     * @param username 用户名称
     * @return 用户列表
     */
    @Override
    public List<User> getListUserByUsername(final String username) {
        return (List<User>) hibernateTemplate.find("from User WHERE username = ?", username);
    }

    /**
     * 根据用户id和用户名获取用户.
     *
     * @param id       id
     * @param username 用户名
     * @return 用户列表
     */
    @Override
    public List<User> getListUserByIdAndUsername(final int id, final String username) {
        return (List<User>) hibernateTemplate.find("from User WHERE id = ? and username = ?", id, username);
    }

    /**
     * 根据用户名获取用户.
     *
     * @param username 用户名
     * @return 用户
     */
    @Override
    public List<User> getListUserByUsernameLike(final String username) {
        return (List<User>) hibernateTemplate.find("from User WHERE username like ?", "%" + username + "%");
    }

    /**
     * 根据id 获取用户.
     *
     * @param id id
     * @return 用户
     */
    @Override
    public User getUser(final int id) {
        return hibernateTemplate.get(User.class, id);
    }

    /**
     * 添加用户.
     *
     * @param user 用户
     * @return 新添加的用户
     */
    @Override
    public User addUser(final User user) {
        hibernateTemplate.save(user);
        return user;
    }

    /**
     * 更新用户.
     *
     * @param user 用户
     * @return 更新过后的用户
     */
    @Override
    public User updateUser(final User user) {
        hibernateTemplate.saveOrUpdate(user);
        return user;
    }

    /**
     * 删除用户.
     *
     * @param user 用户
     */
    @Override
    public void deleteUser(final User user) {
        hibernateTemplate.delete(user);
    }

    /**
     * 得到用户数.
     *
     * @return 用户数
     */
    @Override
    public long countUser() {
        String hql = "select count(*) from User";
        return (Long) hibernateTemplate.find(hql).listIterator().next();
    }

    /**
     * 分页得到用户.
     *
     * @param hql         hql 语句
     * @param params      参数值
     * @param currentPage 第几页
     * @param pageSize    多少条
     * @return 用户列表
     */
    public Page<User> getUserListPage(final String hql, final Object[] params, final int currentPage, final int pageSize) {
        return pageTemplate.getPage(hql, params, currentPage, pageSize);
    }
}
