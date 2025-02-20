package com.example.demo.thread;

/**
 * @Author: zhoujing
 * @Date: 2023/10/8 10:40
 * @Description: volatile
 */
public class Signal {

//    private static int signal = 0;
    private static volatile int signal = 0;

    public static void main(String[] args) throws Exception {
        new Thread(new ThreadA()).start();
        Thread.sleep(1000);
        new Thread(new ThreadB()).start();
    }

    static class ThreadA implements Runnable {

        @Override
        public void run() {
            while (signal < 5) {
                if (signal % 2 == 0) {
                    System.out.println("ThreadA: " + signal);
                    signal++;
                }
            }
        }
    }

    static class ThreadB implements Runnable {

        @Override
        public void run() {
            while (signal < 5) {
                if (signal % 2 == 1) {
                    System.out.println("ThreadB: " + signal);
                    signal = signal + 1;
                }
            }
        }
    }
}
