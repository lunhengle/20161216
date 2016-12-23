package com.lhl.webservice20161216.server.impl;

import org.springframework.stereotype.Service;

import javax.jws.WebService;

/**
 * Created by lunhengle on 2016/12/19.
 */
@Service("helloWorld")
@WebService(endpointInterface = "com.lhl.webservice20161216.client.HelloWorld", serviceName = "helloWorld")
public class HelloWorldImpl implements com.lhl.webservice20161216.client.HelloWorld {
    public String sayHello(String text) {
        return "Hello " + text;
    }
}
