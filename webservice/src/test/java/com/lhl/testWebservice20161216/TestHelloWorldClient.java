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
@ContextConfiguration(locations = {"classpath:spring/ws/applicationContext-ws-client.xml"})
public class TestHelloWorldClient {
    private static Logger logger = LoggerFactory.getLogger(TestHelloWorldClient.class);
    @Autowired
    private HelloWorld helloWorld;

    @Test
    public void testHelloWorldClient() {
        String result = helloWorld.sayHello("world! 你好 世界！");
        logger.info(result);
    }


}
