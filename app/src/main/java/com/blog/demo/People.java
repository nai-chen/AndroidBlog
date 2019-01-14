package com.blog.demo;

public class People {
    public int id;
    public String name;
    public String addr;
    public int age;

    public People() {
    }

    public People(int id, String name, String addr, int age) {
        this.id = id;
        this.name = name;
        this.addr = addr;
        this.age = age;
    }

    public String description() {
        return id + ":" + name + ":" + addr + ":" + age;
    }
}
