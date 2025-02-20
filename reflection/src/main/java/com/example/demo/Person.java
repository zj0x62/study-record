package com.example.demo;

/**
 * @Author: zhoujing
 * @Date: 2025/2/13 14:50
 * @Description:
 */
public class Person {

    public String name;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void hello() {
        System.out.println("Person:hello");
    }
}
