package com.example.demo.thread;

/**
 * @Author: zhoujing
 * @Date: 2025/2/18 10:22
 * @Description:
 */
public class Test {

    public static void main(String[] args) throws Exception {
        AddThread add = new AddThread();
        DecThread dec = new DecThread();
        add.start();
        dec.start();
        add.join();
        dec.join();
        System.out.println(Counter.count);
    }

    static class Counter {
        public static int count = 0;

        public static final Object lock = new Object();
    }

    static class AddThread extends Thread {

        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                synchronized(Counter.lock) {
                    Counter.count += 1;
                }
            }
        }
    }

    static class DecThread extends Thread {

        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                synchronized (Counter.lock) {
                    Counter.count -= 1;
                }
            }
        }
    }
}
