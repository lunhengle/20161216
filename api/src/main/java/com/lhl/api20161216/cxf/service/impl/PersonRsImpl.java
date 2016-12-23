package com.lhl.api20161216.cxf.service.impl;


import com.lhl.api20161216.cxf.service.PersonRs;
import com.lhl.api20161216.cxf.vo.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lunhengle on 2016/12/20.
 * 人实现类
 */
@Service("personWs")
@Produces({MediaType.APPLICATION_XML})
public class PersonRsImpl implements PersonRs {
    private static Logger logger = LoggerFactory.getLogger(PersonRsImpl.class);
    private static Map<String, Person> ps = new HashMap<>();

    static {
        for (int i = 0; i < 5; i++) {
            Person person = new Person();
            person.setId("id" + i);
            person.setName("姓名" + i);
            person.setDescription("描述" + i);
            ps.put(person.getId(), person);
        }
    }

    /**
     * 根据id 获取人.
     *
     * @param id id
     * @return 人
     */
    @Override
    @GET
    @Path(value = "/{id}")
    public Person findPersonById(@PathParam(value = "id") String id) {
        logger.info("根据id 获取人");
        return ps.get(id);
    }

    /**
     * 获取所有人.
     *
     * @return 所有人
     */
    @Override
    @GET
    public List<Person> findAllPerson() {
        logger.info("获取所有人");
        return new ArrayList<>(ps.values());
    }
}
