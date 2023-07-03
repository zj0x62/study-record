package com.example.demo.designpattern.singleton;

/**
 * @Author: zhoujing
 * @Date: 2022/11/23 11:05
 * @Description: 单例模式
 *  双重校验锁-线程安全（懒汉式的改进）
 *  INSTANCE 只需要被实例化一次，之后就可以直接使用了。加锁操作只需要对实例化那部分的代码进行，只有当 INSTANCE 没有被实例化时，才需要进行加锁。
 *  双重校验锁先判断 INSTANCE 是否已经被实例化，如果没有被实例化，那么才对实例化语句进行加锁。
 */
public class Singleton3 {

    /**
     * INSTANCE = new Singleton(); 这段代码其实是分为三步执行。分
     *  1.配内存空间
     *  2.初始化对象
     *  3.将 INSTANCE 指向分配的内存地址
     * 但是由于 JVM 具有指令重排的特性，有可能执行顺序变为了 1>3>2，这在单线程情况下自然是没有问题。但如果是多线程下，有可能获得是一个还没有被初始化的实例，以致于程序出错。
     * 使用 volatile 可以禁止 JVM 的指令重排，保证在多线程环境下也能正常运行。
     */
    private volatile static Singleton3 INSTANCE;

    private Singleton3() {
    }

    public static Singleton3 getInstance() {
        if (INSTANCE == null) {
            synchronized (Singleton3.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Singleton3();
                }
            }
        }
        return INSTANCE;
    }
}
