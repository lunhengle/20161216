package com.lhl.security20161216;

import com.lhl.security20161216.service.EncryptUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试加密类.
 * Created by lunhengle on 2017/1/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/applicationContext-base.xml")
public class TestEncrypt {
    /**
     * 加密.
     */
    @Test
    public void testEncrypt() {
        for (int i = 0; i < 10; i++) {
            System.out.println(EncryptUtil.encrypt("123456"));
        }
    }

    /**
     * 查看两个密码是否一致.
     */
    @Test
    public void testMatch(){
        Assert.assertTrue(EncryptUtil.match("123456","e1619a3bd6637bb36623fe71078dd51253d3e7c153532917bfcd7bbf086ea3d358c49597cffc1032"));
    }
}
