package com.example.demo.designpattern.factory;

/**
 * @Author: zhoujing
 * @Date: 2022/11/23 13:41
 * @Description: 工厂A类 - 生产A类产品
 */
public class FactoryA extends Factory{
    @Override
    public Product manufacture() {
        return new ProductA();
    }
}
