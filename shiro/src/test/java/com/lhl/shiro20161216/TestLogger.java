package com.lhl.shiro20161216;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lunhengle on 2017/1/18.
 */
public class TestLogger {
    private static Logger logger = LoggerFactory.getLogger(TestLogger.class);

    @Test
    public void testLogger() {
        logger.info("测试日志！");
    }
}
