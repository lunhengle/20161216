package com.lhl.security20161216.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

/**
 * Created by lunhengle on 2017/1/17.
 * 加密工具类.
 */
public class EncryptUtil {
    /**
     * 得到加密类.
     */
    private static final PasswordEncoder passwordEncoder = new StandardPasswordEncoder(Constants.ENCRYPT_KEY);

    /**
     * 加密.
     *
     * @param rawPassword 需要加密字段
     * @return 返回加密过后的字段
     */
    public static String encrypt(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    /**
     * 比较密码是否一致.
     *
     * @param rawPassword 需要加密的字段
     * @param password    密码字段
     * @return true 一致 false  不一致
     */
    public static boolean match(String rawPassword, String password) {
        return passwordEncoder.matches(rawPassword, password);
    }
}
