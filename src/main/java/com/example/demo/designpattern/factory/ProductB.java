package com.example.demo.designpattern.factory;

/**
 * @Author: zhoujing
 * @Date: 2022/11/23 13:40
 * @Description: 具体产品B类
 */
public class ProductB extends Product{
    @Override
    public void show() {
        System.out.println("生产出了产品B");
    }
}
