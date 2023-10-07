package com.example.demo.designpattern.factory;

/**
 * @Author: zhoujing
 * @Date: 2022/11/23 13:43
 * @Description: 工厂B类 - 生产B类产品
 */
public class FactoryB extends Factory{
    @Override
    public Product manufacture() {
        return new ProductB();
    }
}
