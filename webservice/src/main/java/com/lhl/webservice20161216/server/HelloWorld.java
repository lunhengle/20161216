package com.lhl.webservice20161216.server;

import javax.jws.WebService;

/**
 * Created by lunhengle on 2016/12/19.
 * 服务端HelloWorld
 */
@WebService
public interface HelloWorld {
    String sayHello(String text);
}
