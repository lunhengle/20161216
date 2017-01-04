package com.lhl.utils20161216.bean.hi;


import javax.persistence.*;

/**
 * Created by lunhengle on 2017/1/3.
 */
@Entity
@Table(name = "USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "PASSWORD")
    private String password;

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
}
