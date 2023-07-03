package com.example.demo.designpattern.builder;

/**
 * @Author: zhoujing
 * @Date: 2022/11/23 14:27
 * @Description:
 */
public class StringBuilder extends AbstractStringBuilder{

    public StringBuilder() {
        super(16);
    }

    @Override
    public String toString() {
        return new String(value, 0, count);
    }
}
