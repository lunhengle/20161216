package com.lhl.utils20161216.orm.jpa;

import com.lhl.utils20161216.bean.jpa.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lunhengle on 2016/12/27.
 */
@Repository("usreDao")
public interface UserDaoIface extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM USER u")
    List<User> getUsers();

}
