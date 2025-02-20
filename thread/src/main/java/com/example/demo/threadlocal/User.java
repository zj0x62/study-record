package com.example.demo.threadlocal;

/**
 * @Author: zhoujing
 * @Date: 2025/2/20 9:51
 * @Description:
 */
public class User {

    public User(String name) {
        this.name = name;
    }

    public String name;

    @Override
    public String toString() {
        return name;
    }
}
