package com.example.demo.threadlocal;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;
import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: zhoujing
 * @Date: 2025/2/20 10:45
 * @Description: TransmittableThreadLocal:在使用线程池等会池化复用线程的执行组件情况下，提供ThreadLocal值的传递功能，解决异步执行时上下文传递的问题。
 *               保证线程池中传递值：1. 修饰Runnable和Callable
 *                               2. 修饰线程池
 */
public class TransmittableThreadLocalDemo {

    public static TransmittableThreadLocal<User> threadLocal = new TransmittableThreadLocal<>();

    public static ExecutorService executor = Executors.newFixedThreadPool(5);
    // 修饰线程池
    public static ExecutorService ttlExecutor = TtlExecutors.getTtlExecutorService(executor);

    public static void main(String[] args) throws Exception {
        // 修饰Runnable
        processUserPool();
        Thread.sleep(1000);
        System.out.println("----------------------------------");
        // 修饰线程池
        processUserPool2();

        executor.shutdown();
    }

    public static void processUserPool() {
        processUserPool("张三");
        processUserPool("李四");
        processUserPool("王五");
    }

    public static void processUserPool(String name) {
        try {
            threadLocal.set(new User(name));
            // 修饰Runnable
            Runnable task1 = TtlRunnable.get(new Task1());
            Runnable task2 = TtlRunnable.get(new Task2());
            Runnable task3 = TtlRunnable.get(new Task3());
            executor.submit(task1);
            executor.submit(task2);
            executor.submit(task3);
        } finally {
            threadLocal.remove();
        }
    }

    public static void processUserPool2() {
        processUserPool2("张三");
        processUserPool2("李四");
        processUserPool2("王五");
    }

    public static void processUserPool2(String name) {
        try {
            threadLocal.set(new User(name));
            Runnable task1 = new Task1();
            Runnable task2 = new Task2();
            Runnable task3 = new Task3();
            // 修饰线程池
            ttlExecutor.submit(task1);
            ttlExecutor.submit(task2);
            ttlExecutor.submit(task3);
        } finally {
            threadLocal.remove();
        }
    }

    private static void step1 () {
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

    static class Task1 implements Runnable {

        @Override
        public void run() {
            step1();
        }
    }

    static class Task2 implements Runnable {

        @Override
        public void run() {
            step2();
        }
    }

    static class Task3 implements Runnable {

        @Override
        public void run() {
            step3();
        }
    }

}
