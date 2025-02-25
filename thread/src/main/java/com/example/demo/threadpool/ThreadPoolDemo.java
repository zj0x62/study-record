package com.example.demo.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zhoujing
 * @Date: 2025/2/19 13:07
 * @Description: 线程池的状态：
 *                  RUNNING：接收新任务并处理排队任务
 *                  SHUTDOWN：不接受新任务，但处理排队任务
 *                  STOP：不接受新任务，也不处理排队任务，并中断正在进行的任务
 *                  TIDYING：所有任务都已终止，workerCount为0时，线程会转换到TIDYING状态，并将执行terminated()钩子方法
 *                  TERMINATED：terminated()运行完成
 */
public class ThreadPoolDemo {

    public void createExecutor() {
        // 自定义线程池
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                5,                           // 核心线程数
                10,                                     // 最大线程数
                60L,                                    // 线程存活时间
                TimeUnit.SECONDS,                       // 线程存活时间单位
                new ArrayBlockingQueue<>(100),  // 阻塞队列
                Executors.defaultThreadFactory(),       // 线程工厂
                new ThreadPoolExecutor.AbortPolicy()    // 拒绝策略
        );
        executor.allowCoreThreadTimeOut(true);
        executor.allowsCoreThreadTimeOut();

        // 自定义线程池 重写terminated()方法
        // 线程池关闭后, 会执行terminated()方法
        ThreadPoolExecutor executor2 = new ThreadPoolExecutor(
                5,                           // 核心线程数
                10,                                     // 最大线程数
                60L,                                    // 线程存活时间
                TimeUnit.SECONDS,                       // 线程存活时间单位
                new ArrayBlockingQueue<>(100),  // 阻塞队列
                Executors.defaultThreadFactory(),       // 线程工厂
                new ThreadPoolExecutor.AbortPolicy()    // 拒绝策略
        ) {
            @Override
            protected void terminated() {
                super.terminated();
                System.out.println("执行 terminated() 方法");
            }
        };

        // 缓存线程池(允许的创建线程数量为Integer.MAX_VALUE, 可能会创建大量的线程, 从而导致OOM。)
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        ThreadPoolExecutor cachedThreadPool2 = new ThreadPoolExecutor(
                0,
                Integer.MAX_VALUE,
                60L,
                TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());

        // 固定线程池(允许的请求队列长度为Integer.MAX_VALUE, 可能会堆积大量的请求, 从而导致OOM。)
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
        ThreadPoolExecutor fixedThreadPool2 = new ThreadPoolExecutor(
                10,
                10,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());

        // 单线程池(允许的请求队列长度为Integer.MAX_VALUE, 可能会堆积大量的请求, 从而导致OOM。)
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
//        ThreadPoolExecutor singleThreadExecutor2 = new Executors.FinalizableDelegatedExecutorService(
//                new ThreadPoolExecutor(
//                        1,
//                        1,
//                        0L,
//                        TimeUnit.MILLISECONDS,
//                        new LinkedBlockingQueue<Runnable>())
//        );

        // 定时任务线程池(允许的请求队列长度为Integer.MAX_VALUE, 可能会堆积大量的请求, 从而导致OOM。)
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(10);
//        ThreadPoolExecutor scheduledThreadPool2 = new ThreadPoolExecutor(
//                10,
//                Integer.MAX_VALUE,
//                0L,
//                TimeUnit.NANOSECONDS,
//                new ScheduledThreadPoolExecutor.DelayedWorkQueue()
//        );
    }
}
