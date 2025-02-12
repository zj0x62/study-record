package com.example.demo;

/**
 * @Author: zhoujing
 * @Date: 2025/2/6 11:03
 * @Description: 枚举单例(线程安全) Effective Java 作者推荐
 *               这种方式解决了最主要的；线程安全、自由串行化、单一实例。
 *               这种写法在功能上与共有域方法相近，但是它更简洁，无偿地提供了串行化机制，绝对防止对此实例化，即使是在面对复杂的串行化或者反射攻击的时候。
 */
public enum Singleton_07 {

    INSTANCE;

    public void test() {
        System.out.println("枚举单例");
    }

    public static void main(String[] args) {
        Singleton_07.INSTANCE.test();
        System.out.println(Singleton_07.INSTANCE);
    }
}
