package com.example.demo.designpattern.factory;

/**
 * @Author: zhoujing
 * @Date: 2022/11/23 13:40
 * @Description: 具体产品A类
 */
public class ProductA extends Product{
    @Override
    public void show() {
        System.out.println("生产出了产品A");
    }
}
