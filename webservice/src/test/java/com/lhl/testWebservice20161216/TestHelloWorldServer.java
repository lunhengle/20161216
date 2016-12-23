package com.lhl.testWebservice20161216;

import com.lhl.webservice20161216.client.HelloWorld;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by lunhengle on 2016/12/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/ws/applicationContext-ws-server.xml"})
public class TestHelloWorldServer {
    private static Logger logger = LoggerFactory.getLogger(TestHelloWorldServer.class);
    @Autowired
    private HelloWorld helloWorld;

    /**
     * 测试helloWorld.
     */
    @Test
    public void testHelloWorldServer() {
        String result = helloWorld.sayHello("你好");
        logger.info(result);
    }
}
