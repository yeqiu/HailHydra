package com.yeqiu.hailhydra.data;

import org.litepal.crud.LitePalSupport;

import java.io.Serializable;

/**
 * @project：Xbzd
 * @author：小卷子
 * @date 2018/8/20
 * @describe：
 * @fix：
 */
public class User extends LitePalSupport implements Serializable{


    private int id;
    private String name;
    private int age;
    private int grander;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGrander() {
        return grander;
    }

    public void setGrander(int grander) {
        this.grander = grander;
    }
}
