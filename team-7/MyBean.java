package com.xc.jackson.annotation;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 Created by Administrator on 2016/9/6.
 */
@JsonPropertyOrder({"name","id"})
public class MyBean {
    public int id;

    private String name;

    public MyBean() {
    }

    public MyBean(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @JsonGetter("name")
    public String getName() {
        return name;
    }

    @JsonGetter("id")
    public int getId() {
        return id;
    }
}
