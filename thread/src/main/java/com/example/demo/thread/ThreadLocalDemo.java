package com.example.demo.thread;

/**
 * @Author: zhoujing
 * @Date: 2023/10/8 15:04
 * @Description: ThreadLocal是一个本地线程副本变量工具类。内部是一个弱引用的Map来维护。
 *               ThreadLocal类并不属于多线程间的通信，而是让每个线程有自己“独立”的变量，线程之间互不影响。它为每个线程都创建一个副本，每个线程可以访问自己内部的副本变量。
 */
public class ThreadLocalDemo {

    public static void main(String[] args) throws Exception {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        new Thread(new ThreadA(threadLocal)).start();
        Thread.sleep(1000);
        new Thread(new ThreadB(threadLocal)).start();
    }

    static class ThreadA implements Runnable {
        private ThreadLocal<String> threadLocal;

        public ThreadA(ThreadLocal<String> threadLocal) {
            this.threadLocal = threadLocal;
        }

        @Override
        public void run() {
            threadLocal.set("A");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("ThreadA输出：" + threadLocal.get());
        }
    }

    static class ThreadB implements Runnable {
        private ThreadLocal<String> threadLocal;

        public ThreadB(ThreadLocal<String> threadLocal) {
            this.threadLocal = threadLocal;
        }

        @Override
        public void run() {
            threadLocal.set("B");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("ThreadB输出：" + threadLocal.get());
        }
    }
}
