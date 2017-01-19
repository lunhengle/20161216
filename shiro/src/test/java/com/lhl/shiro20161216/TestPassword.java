package com.lhl.shiro20161216;

import com.lhl.shiro20161216.service.EncryptUtil;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 测试加密.
 * Created by lunhengle on 2017/1/19.
 */
public class TestPassword {
    /**
     * 日志.
     */
    private static Logger logger = LoggerFactory.getLogger(TestPassword.class);

    /**
     * 测试加密.
     */
    @Test
    public void testEncrypt() {
        String encryptPassword = EncryptUtil.encrypt("user", "123456");
        logger.info(encryptPassword);
        encryptPassword = EncryptUtil.encrypt("user1", "123456");
        logger.info(encryptPassword);
        encryptPassword = EncryptUtil.encrypt("admin", "123456");
        logger.info(encryptPassword);
        encryptPassword = EncryptUtil.encrypt("superadmin", "123456");
        logger.info(encryptPassword);
    }
}
