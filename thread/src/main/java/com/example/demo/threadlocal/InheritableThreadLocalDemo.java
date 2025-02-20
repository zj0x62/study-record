package com.example.demo.threadlocal;

import cn.hutool.core.util.ObjectUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: zhoujing
 * @Date: 2025/2/20 10:20
 * @Description: InheritableThreadLocal 可以让子线程继承父线程的 ThreadLocal 数据。
 *               仅适用于直接通过 new Thread() 创建的子线程，不适用于线程池（线程池复用线程，不会重新复制数据）。
 */
public class InheritableThreadLocalDemo {

    public static InheritableThreadLocal<User> threadLocal = new InheritableThreadLocal<>();

    public static ExecutorService executor = Executors.newFixedThreadPool(5);

    public static void main(String[] args) throws Exception {
        processUserThread();
        Thread.sleep(1000);
        System.out.println("-------------------------");
        processUserPool();

        executor.shutdown();
    }

    public static void processUserThread() {
        processUserThread("张三");
        processUserThread("李四");
        processUserThread("王五");
    }

    public static void processUserThread(String name) {
        try {
            threadLocal.set(new User(name));
            new Thread(InheritableThreadLocalDemo::step1).start();
            new Thread(InheritableThreadLocalDemo::step2).start();
            new Thread(InheritableThreadLocalDemo::step3).start();
        } finally {
            threadLocal.remove();
        }
    }

    public static void processUserPool() {
        processUserPool("张三");
        processUserPool("李四");
        processUserPool("王五");
    }

    public static void processUserPool(String name) {
        try {
            threadLocal.set(new User(name));
            executor.submit(InheritableThreadLocalDemo::step1);
            executor.submit(InheritableThreadLocalDemo::step2);
            executor.submit(InheritableThreadLocalDemo::step3);
        } finally {
            threadLocal.remove();
        }
    }

    private static void step1() {
        User user = threadLocal.get();
        System.out.println("step1: " + ObjectUtil.toString(user) + ", thread id: " + Thread.currentThread().getName());
    }

    private static void step2() {
        User user = threadLocal.get();
        System.out.println("step2: " + ObjectUtil.toString(user) + ", thread id: " + Thread.currentThread().getName());
    }

    private static void step3() {
        User user = threadLocal.get();
        System.out.println("step3: " + ObjectUtil.toString(user) + ", thread id: " + Thread.currentThread().getName());
    }
}
