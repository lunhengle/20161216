package com.lhl.api20161216.cxf.vo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by lunhengle on 2016/12/20.
 * 实体类
 */
@XmlRootElement(name = "person")
public class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * id.
     */
    private String id;
    /**
     * 名称.
     */
    private String name;
    /**
     * 描述.
     */
    private String description;

    @XmlElement(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlElement(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Person description = " + description + " name = " + name + " id = " + id;
    }
}
