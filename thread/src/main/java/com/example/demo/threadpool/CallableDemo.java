package com.example.demo.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zhoujing
 * @Date: 2025/2/19 16:36
 * @Description:
 */
public class CallableDemo {

    public static void main(String[] args) {
//        taskExecute();
        taskExecute2();
    }

    public static void taskExecute() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2,
                4,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2)
        );

        for (int i = 1; i <= 6; i++) {
            Future<String> future = executor.submit(new CallableTask(i));
            try {
                // future.get() 导致主线程阻塞等待当前任务完成，才继续提交下一个任务。所以会变成串行
                String s = future.get();
                System.out.println(s);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        executor.shutdown();
    }

    public static void taskExecute2() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2,
                4,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2)
        );

        // 将任务提交与结果获取分离，使线程池并行处理多个任务
        List<Future<String>> list = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            Future<String> future = executor.submit(new CallableTask(i));
            list.add(future);
        }

        for (Future<String> future : list) {
            try {
                String s = future.get();
                System.out.println(s);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

        executor.shutdown();
    }

    static class CallableTask implements Callable<String> {

        int index;

        public CallableTask(int index) {
            this.index = index;
        }

        @Override
        public String call() throws Exception {
            System.out.println("callable task" + index +" start");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("callable task" + index +" end");

            return index + ": " + Math.random();
        }
    }
}
