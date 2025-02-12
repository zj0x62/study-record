package com.example.demo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: zhoujing
 * @Date: 2025/2/6 10:12
 * @Description: 静态类
 *              这种方式在我们平常的业务开发中非常常见，这样静态类的方式可以在第一次运行的时候直接初始化Map类，同时这里我们也不需要到延迟加载再使用。
 *              在不需要维持任何状态下，仅仅用于全局访问，这个使用静态类的方式更加方便。
 *              但如果需要被继承以及需要维持一些特定状态的情况下，就适合使用单例模式。
 */
public class Singleton_00 {

    public static Map<String, String> cache = new ConcurrentHashMap<>();
}
