package com.example.demo.designpattern.singleton;

/**
 * @Author: zhoujing
 * @Date: 2022/11/23 11:02
 * @Description: 单例模式
 *  懒汉式 - 线程安全
 *  只需要对 getInstance() 方法加锁，那么在一个时间点只能有一个线程能够进入该方法，从而避免了多次实例化 uniqueInstance 的问题。
 *  但是当一个线程进入该方法之后，其它试图进入该方法的线程都必须等待，因此性能上有一定的损耗。
 */
public class Singleton2 {

    private static Singleton2 INSTANCE;

    private Singleton2() {
    }

    public static synchronized Singleton2 getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Singleton2();
        }
        return INSTANCE;
    }
}
