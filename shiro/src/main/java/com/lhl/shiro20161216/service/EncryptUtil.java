package com.lhl.shiro20161216.service;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * 加密工具.
 * Created by lunhengle on 2017/1/19.
 */
public class EncryptUtil {
    /**
     * 加密方法.
     *
     * @param salt      盐值
     * @param sourceStr 要加密的字段
     * @return 加密后的字段
     */
    public static String encrypt(String salt, String sourceStr) {
        return new Md5Hash(sourceStr, salt).toString();
    }
}
