package com.example.demo.designpattern.factory;

/**
 * @Author: zhoujing
 * @Date: 2022/11/23 13:44
 * @Description: 生产工作流程
 */
public class FactoryPattern {
    public static void main(String[] args) {
        //生产产品A
        FactoryA factoryA = new FactoryA();
        factoryA.manufacture().show();

        //生产产品B
        FactoryB factoryB = new FactoryB();
        factoryB.manufacture().show();
    }
}
