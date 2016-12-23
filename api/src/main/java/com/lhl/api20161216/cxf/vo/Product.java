package com.lhl.api20161216.cxf.vo;

import java.io.Serializable;

/**
 * Created by lunhengle on 2016/12/22.
 * 产品类型.
 */
public class Product implements Serializable {
    private static final long serialVersionUID = 2l;
    private String id;
    private String name;
    private String type;
    private String level;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
