package com.cmj.swagger.entity;

/**
 * Created by cmj on 2017/8/28.
 */
public class User {
    private  Long Id;
    private  String Name;
    private String Age;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }
}
