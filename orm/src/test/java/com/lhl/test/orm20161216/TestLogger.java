package com.lhl.test.orm20161216;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lunhengle on 2016/12/16.
 * 测试日志是否启用
 */
public class TestLogger {
    private static Logger logger = LoggerFactory.getLogger(TestLogger.class);

    @Test
    public void testLogger() {
        logger.trace("测试日志级别-trace");
        logger.debug("测试日志级别-debug");
        logger.info("测试日志级别-info");
        logger.warn("测试日志级别-warn");
        logger.error("测试日志级别-error");

    }
}
