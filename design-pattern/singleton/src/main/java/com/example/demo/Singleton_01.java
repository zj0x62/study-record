package com.example.demo;

/**
 * @Author: zhoujing
 * @Date: 2025/2/6 10:17
 * @Description: 懒汉模式(线程不安全) 空时加载
 *              单例模式有一个特点就是不允许外部直接创建，也就是new Singleton_01()，因此这里在默认的构造函数上添加了私有属性 private。
 *              目前此种方式的单例确实满足了懒加载，但是如果有多个访问者同时去获取对象实例你可以想象成一堆人在抢厕所，就会造成多个同样的实例并存，从而没有达到单例的要求。
 */
public class Singleton_01 {

    private static Singleton_01 instance;

    private Singleton_01() {}

    public static Singleton_01 getInstance() {
        if (instance == null) {
            instance = new Singleton_01();
        }

        return instance;
    }
}
