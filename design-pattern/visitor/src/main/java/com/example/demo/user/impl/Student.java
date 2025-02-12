package com.example.demo.user.impl;

import com.example.demo.user.User;
import com.example.demo.visitor.Visitor;

/**
 * @Author: zhoujing
 * @Date: 2025/2/11 17:24
 * @Description:
 */
public class Student extends User {

    public Student(String name, String identity, String clazz) {
        super(name, identity, clazz);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public int ranking() {
        return (int) (Math.random() * 100);
    }
}
