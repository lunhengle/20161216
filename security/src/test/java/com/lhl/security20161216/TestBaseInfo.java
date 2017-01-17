package com.lhl.security20161216;

import com.lhl.security20161216.service.BaseInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * 测试baseinfo.
 * Created by lunhengle on 2017/1/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/applicationContext-base.xml")
public class TestBaseInfo {
    private static Logger logger = LoggerFactory.getLogger(TestBaseInfo.class);
    @Autowired
    private BaseInfo baseInfo;

    @Test
    public void testBaseInfo() {
        logger.info(baseInfo.getEncryptKey());
    }
}
