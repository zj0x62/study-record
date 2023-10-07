package com.example.demo.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @Author: zhoujing
 * @Date: 2023/9/26 17:54
 * @Description:
 */
public class ThreadDamo2 {

    public static void main(String[] args) throws Exception {

        ExecutorService service = Executors.newCachedThreadPool();
        FutureTask<Integer> task = new FutureTask<>(new Task());
        service.submit(task);
        System.out.println(task.get());
    }

    public static class Task implements Callable {

        @Override
        public Integer call() throws Exception {
            Thread.sleep(2000);
            return 2;
        }
    }
}
