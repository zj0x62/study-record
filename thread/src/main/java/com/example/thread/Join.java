package com.example.thread;

/**
 * @Author: zhoujing
 * @Date: 2023/10/8 14:43
 * @Description: join()方法是Thread类的一个实例方法。它的作用是让当前线程陷入“等待状态”，等join的这个线程执行完成后，在继续执行当前线程。
 */
public class Join {

    public static void main(String[] args) throws Exception {
        Thread thread = new Thread(new ThreadA());
        thread.start();
        thread.join();
        System.out.println("如果不加join方法，我会先被打印出来，加了就不一样");
    }
    
    static class ThreadA implements Runnable {

        @Override
        public void run() {
            try {
                System.out.println("我是子线程，我先睡一秒");
                Thread.sleep(1000);
                System.out.println("我是子线程，我睡完了一秒");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
