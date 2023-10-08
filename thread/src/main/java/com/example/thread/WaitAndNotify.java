package com.example.thread;

/**
 * @Author: zhoujing
 * @Date: 2023/10/8 9:57
 * @Description: 等待/通知机制
 *               1.notify()方法会随机叫醒一个正在等待的线程，而notifyAll()方法会叫醒所有正在等待的线程。
 *               2.等待/通知机制使用的是使用同一个对象锁，如果两个线程使用的是不同的对象锁，那他们之间是不能用等待/通知机制通信的。
 *               3.sleep方法是不会释放当前的锁的，而wait()方法会。
 *               4.wait释放cpu资源，同时释放锁；sleep释放cpu资源，但是不释放锁，所以易死锁。
 *               5.wait必须放在同步块或同步方法中，而sleep可以在任意位置。
 */
public class WaitAndNotify {

    private static final Object lock = new Object();

    public static void main(String[] args) throws Exception {
        new Thread(new ThreadA()).start();
        Thread.sleep(1000);
        new Thread(new ThreadB()).start();
    }

    static class ThreadA implements Runnable {

        @Override
        public void run() {
            synchronized(lock) {
                for (int i = 0; i < 5; i++) {
                    try {
                        System.out.println("ThreadA: " + i);
                        lock.notifyAll();
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notifyAll();
            }
        }
    }

    static class ThreadB implements Runnable {

        @Override
        public void run() {
            synchronized (lock) {
                for (int i = 0; i < 5; i++) {
                    try {
                        System.out.println("ThreadB: " + i);
                        lock.notifyAll();
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notifyAll();
            }
        }
    }
}
