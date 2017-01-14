package com.lhl.security20161216.bean;

/**
 * 用户表.
 * Created by lunhengle on 2017/1/11.
 */
public class User {
    /**
     * id.
     */
    private int id;
    /**
     * 用户名.
     */
    private String username;
    /**
     * 密码.
     */
    private String password;
    /**
     * 是否启用 1 启用 2 不启用
     */
    private int enabled;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }
}
