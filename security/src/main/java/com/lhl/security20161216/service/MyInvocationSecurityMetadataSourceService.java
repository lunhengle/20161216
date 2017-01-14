package com.lhl.security20161216.service;

import com.lhl.security20161216.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * 访问的资源.
 * Created by lunhengle on 2017/1/13.
 */
@Service("filterInvocationSecurityMetadataSource")
public class MyInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {
    @Autowired
    private UserDao userDao;
    /**
     * 日志.
     */
    private static Logger logger = LoggerFactory.getLogger(MyInvocationSecurityMetadataSourceService.class);
    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

    /**
     * 定义Spring 初始化.
     */
    @PostConstruct
    public void init() {
        loadResourceDefine();
    }

    /**
     * 加载资源.
     */
    private void loadResourceDefine() {
        logger.info("加载访问的资源");
        List<Map<String, Object>> list = userDao.getRoleResources();
        resourceMap = new HashMap<>();
        if (list != null) {
            for (Map map : list) {
                String authName = String.valueOf(map.get("ROLE"));
                ConfigAttribute configAttribute = new SecurityConfig(authName);
                String url = String.valueOf(map.get("URL"));
                if (resourceMap.containsKey(url)) {
                    Collection<ConfigAttribute> configAttributes = resourceMap.get(url);
                    configAttributes.add(configAttribute);
                    resourceMap.put(url, configAttributes);
                } else {
                    Collection<ConfigAttribute> configAttributes = new ArrayList<>();
                    configAttributes.add(configAttribute);
                    resourceMap.put(url, configAttributes);
                }
            }
        }

    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        String url = ((FilterInvocation) o).getRequestUrl();
        int firstQuestionMarkIndex = url.indexOf("?");
        if (firstQuestionMarkIndex != -1) {
            url = url.substring(0, firstQuestionMarkIndex);
        }
        Iterator<String> iterator = resourceMap.keySet().iterator();
        while (iterator.hasNext()) {
            String resUrl = iterator.next();
            if (url.startsWith(resUrl)) {
                return resourceMap.get(resUrl);
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
