package com.example.demo.designpattern.singleton;

/**
 * @Author: zhoujing
 * @Date: 2022/11/23 11:12
 * @Description: 单例模式
 * 饿汉式-线程安全
 * 线程不安全问题主要是由于INSTANCE被多次实例化，采取直接实例化 INSTANCE 的方式就不会产生线程不安全问题。
 * 但是直接实例化的方式也丢失了延迟实例化带来的节约资源的好处。
 */
public class Singleton4 {

    private static Singleton4 INSTANCE = new Singleton4();

    private Singleton4() {
    }

    public static Singleton4 getInstance() {
        return INSTANCE;
    }
}
