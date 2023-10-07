package com.example.thread;

import java.util.stream.IntStream;

/**
 * @Author: zhoujing
 * @Date: 2023/10/7 10:56
 * @Description: ThreadPriority
 */
public class ThreadPriorityDemo {

    public static void main(String[] args) {
//        // 设置线程优先级
//        demo1();
//        // 执行不同优先级的线程
//        demo2();
        // 线程优先级大于线程所在线程组的最大优先级
        demo3();
    }

    private static void demo1() {
        Thread thread = new Thread();
        System.out.println("默认线程优先级：" + thread.getPriority());
        Thread thread2 = new Thread();
        thread2.setPriority(10);
        System.out.println("设置过的线程优先级：" + thread2.getPriority());
    }

    /**
     * Java程序中对线程所设置的优先级只是给操作系统一个建议，操作系统不一定会采纳。而真正的调用顺序，是由操作系统的线程调度算法决定的。
     * Java提供了一个线程调度器来监视和控制处于RUNNABLE状态的线程。线程的调度策略采用 抢占式，优先级高的线程比优先级低的线程会有更大的几率优先执行。
     * 在执行级相同的情况下，按照“先到先得”的原则。每个Java程序都有一个默认的主线程，就是通过jvm启动的第一个main线程。
     */
    private static void demo2() {
        IntStream.range(1, 10).forEach(i -> {
            Thread thread = new Thread(new T1());
            thread.setPriority(i);
            thread.start();
        });
    }

    /**
     * 如果某个线程的优先级大于线程所在线程组的最大优先级，那么该线程的优先级将会失效，取而代之的是线程组的最大优先级。
     */
    private static void demo3() {
        ThreadGroup threadGroup = new ThreadGroup("t1");
        threadGroup.setMaxPriority(6);
        Thread thread = new Thread(threadGroup, "thread");
        thread.setPriority(9);
        System.out.println("线程组的优先级：" + threadGroup.getMaxPriority());
        System.out.println("线程的优先级：" + thread.getPriority());
    }

    public static class T1 extends Thread {

        @Override
        public void run() {
            super.run();
            System.out.printf("当前执行的线程是：%s, 优先级：%d%n", Thread.currentThread().getName(), Thread.currentThread().getPriority());
        }
    }
}
