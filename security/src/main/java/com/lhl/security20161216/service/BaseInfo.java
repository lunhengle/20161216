package com.lhl.security20161216.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by lunhengle on 2017/1/17.
 */
@Service
public class BaseInfo {
    /**
     * 加密key.
     */
    private String encryptKey;

    /**
     * 注入加密key.
     *
     * @param encryptKey 加密key
     */
    @Value("#{base['encrypt.key']}")
    public void setEncryptKey(String encryptKey) {
        Constants.ENCRYPT_KEY = encryptKey;
        this.encryptKey = encryptKey;
    }

    /**
     * 得到encryptKey.
     *
     * @return encryptKey
     */
    public String getEncryptKey() {
        return encryptKey;
    }
}
