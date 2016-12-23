package com.lhl.webservice20161216.client;

import org.springframework.stereotype.Service;

import javax.jws.WebService;

/**
 * Created by lunhengle on 2016/12/19.
 * 客户端 helloWorld
 */
@Service("helloWorldClient")
@WebService
public interface HelloWorld {
    String sayHello(String text);
}
