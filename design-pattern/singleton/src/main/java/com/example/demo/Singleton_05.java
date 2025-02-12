package com.example.demo;

/**
 * @Author: zhoujing
 * @Date: 2025/2/6 10:46
 * @Description: 双检锁(线程安全) 空时 synchronized 再判空
 *              双重锁的方式是方法级锁的优化，减少了部分获取实例的耗时。
 *              同时这种方式也满足了懒加载。
 */
public class Singleton_05 {

    private static volatile Singleton_05 instance;

    private Singleton_05() {}

    public static Singleton_05 getInstance() {
        if (instance == null) {
            synchronized(Singleton_05.class) {
                if (instance == null) {
                    instance = new Singleton_05();
                }
            }
        }

        return instance;
    }
}
