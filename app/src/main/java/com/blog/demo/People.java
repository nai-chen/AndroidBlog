package com.blog.demo;

/**
 * Created by cn on 2017/2/10.
 */

public class People {
    private int id;
    private String name;
    private String addr;
    private int age;

    public People(int id, String name, String addr, int age) {
        this.id = id;
        this.name = name;
        this.addr = addr;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddr() {
        return addr;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return id + ":" + name + ":" + addr + ":" + age;
    }

}
