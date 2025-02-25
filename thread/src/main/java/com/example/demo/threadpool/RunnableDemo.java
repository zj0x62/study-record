package com.example.demo.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zhoujing
 * @Date: 2025/2/19 16:35
 * @Description:
 */
public class RunnableDemo {

    public static void main(String[] args) {
        taskExecute();
    }

    public static void taskExecute() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2,
                4,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2)
        );

        // 提交6个任务（T1 - T6）
        // T1、T2由核心线程直接执行
        // T3、T4进入队列
        // T5、T6触发创建非核心线程(线程数从2扩容到4)并立即执行
        for (int i = 1; i <= 6; i++) {
            executor.submit(new Task(i));
        }

        executor.shutdown();
    }

    static class Task implements Runnable {

        int index;

        public Task(int index) {
            this.index = index;
        }

        @Override
        public void run() {
            System.out.println("task" + index +" start");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("task" + index +" end");
        }
    }
}
