package com.lhl.api20161216.cxf.service;


import com.lhl.api20161216.cxf.vo.Person;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;


/**
 * Created by lunhengle on 2016/12/20.
 * person ws 接口
 */
@Produces({MediaType.APPLICATION_XML})
public interface PersonRs {
    /**
     * 根据id 获取人.
     *
     * @param id id
     * @return 人
     */
    @GET
    @Path(value = "/{id}")
    Person findPersonById(@PathParam("id") String id);

    /**
     * 获取所有人.
     *
     * @return 所有人
     */
    @GET
    List<Person> findAllPerson();

}
