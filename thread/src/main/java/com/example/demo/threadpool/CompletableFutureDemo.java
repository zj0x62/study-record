package com.example.demo.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zhoujing
 * @Date: 2025/2/19 16:52
 * @Description:
 */
public class CompletableFutureDemo {

    public static ThreadPoolExecutor executor = new ThreadPoolExecutor(
            2,
            5,
            60,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(5));

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            task();
        }

        executor.shutdown();
    }

    public static void task() {
        // 创建异步执行任务
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(CompletableFutureDemo::fetchPrice, executor);
        // 如果执行成功
        cf.thenAccept((result) -> {
            System.out.println("price: " + result);
        });
        // 如果执行异常
        cf.exceptionally((e) -> {
            System.out.println("exception: " + e.getMessage());
            return null;
        });
    }

    static Double fetchPrice() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (Math.random() < 0.3) {
            throw new RuntimeException("fetch price failed");
        }

        return 5 + Math.random() * 20;
    }
}
