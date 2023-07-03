package com.example.demo.designpattern.singleton;

/**
 * @Author: zhoujing
 * @Date: 2022/11/23 10:56
 * @Description: 单例模式
 *  懒汉式-线程不安全
 *  以下实现中，私有静态变量 uniqueInstance 被延迟实例化，这样做的好处是，如果没有用到该类，那么就不会实例化 uniqueInstance，从而节约资源。
 *  这个实现在多线程环境下是不安全的，如果多个线程能够同时进入 if (uniqueInstance == null) ，并且此时 uniqueInstance 为 null，
 *  那么会有多个线程执行 uniqueInstance = new Singleton(); 语句，这将导致多次实例化 uniqueInstance。
 */
public class Singleton {

    private static Singleton INSTANCE;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Singleton();
        }
        return INSTANCE;
    }
}
