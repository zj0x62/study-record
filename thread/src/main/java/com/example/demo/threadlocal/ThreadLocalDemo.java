package com.example.demo.threadlocal;

import cn.hutool.core.util.ObjectUtil;

/**
 * @Author: zhoujing
 * @Date: 2025/2/20 9:50
 * @Description: ThreadLocal 是线程隔离的，每个线程有独立的数据副本。仅适用于同一线程内，不适用于线程池。
 */
public class ThreadLocalDemo {

    public static ThreadLocal<User> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) throws Exception {
        processUser();
        Thread.sleep(1000);
        System.out.println("-------------------------");
        processUserThread();
    }

    public static void processUser() {
        try {
            threadLocal.set(new User("张三"));
            step1();
            step2();
            step3();
        } finally {
            threadLocal.remove();
        }
    }

    public static void processUserThread() {
        try {
            threadLocal.set(new User("张三"));
            new Thread(ThreadLocalDemo::step1).start();
            new Thread(ThreadLocalDemo::step2).start();
            new Thread(ThreadLocalDemo::step3).start();
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
