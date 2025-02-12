package com.example.demo.user.impl;

import com.example.demo.user.User;
import com.example.demo.visitor.Visitor;

import java.math.BigDecimal;

/**
 * @Author: zhoujing
 * @Date: 2025/2/11 17:24
 * @Description:
 */
public class Teacher extends User {

    public Teacher(String name, String identity, String clazz) {
        super(name, identity, clazz);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    // 升本率
    public double entranceRatio() {
        return BigDecimal.valueOf(Math.random() * 100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}