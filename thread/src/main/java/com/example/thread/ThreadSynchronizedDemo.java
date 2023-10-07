package com.example.thread;

/**
 * @Author: zhoujing
 * @Date: 2023/10/7 16:04
 * @Description:
 */
public class ThreadSynchronizedDemo {

    private static final Object lock = new Object();

    public static void main(String[] args) throws Exception{
//        demo();
        demo2();
    }

    /**
     * 无锁，执行结果每次都不一样
     */
    private static void demo() {
        new Thread(new ThreadA()).start();
        new Thread(new ThreadB()).start();
    }

    /**
     * 加锁，当线程A执行完后才会执行线程B
     */
    private static void demo2() throws Exception {
        new Thread(new ThreadLockA()).start();
        // 这里在主线程里使用sleep方法睡眠了10毫秒，是为了防止B先获得锁。因为如果同时start，线程A和线程B都是处于就绪状态，操作系统可能会让B先运行。
        // 这样就会先输出B的内容，然后B执行完后自动释放锁，线程A再执行。
        Thread.sleep(10);
        new Thread(new ThreadLockB()).start();
    }

    static class ThreadA implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                System.out.println("Thread A " + i);
            }
        }
    }

    static class ThreadB implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                System.out.println("Thread B " + i);
            }
        }
    }

    static class ThreadLockA implements Runnable {

        @Override
        public void run() {
            synchronized (lock) {
                for (int i = 0; i < 100; i++) {
                    System.out.println("ThreadLock A " + i);
                }
            }
        }
    }

    static class ThreadLockB implements Runnable {

        @Override
        public void run() {
            synchronized(lock) {
                for (int i = 0; i < 100; i++) {
                    System.out.println("ThreadLock B " + i);
                }
            }
        }
    }
}
