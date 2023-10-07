//package com.example.demo.thread;
//
//import java.util.concurrent.Callable;
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.Future;
//import java.util.concurrent.FutureTask;
//
///**
// * @Author: zhoujing
// * @Date: 2023/8/24 18:02
// * @Description:
// */
//public class ThreadDemo {
//
//    public static void main(String[] args) throws Exception {
////        new MyThread().start();
////        new Thread(new MyThread2()).start();
////        new Thread(() -> System.out.println("匿名内部类")).start();
//
//        ExecutorService executor = Executors.newCachedThreadPool();
//        Task task = new Task();
//        Future<Integer> future = executor.submit(task);
//        System.out.println(future.get());
//
//        ExecutorService executor = Executors.newCachedThreadPool();
//        FutureTask<Integer> futureTask = new FutureTask<>(new Task());
//        executor.submit(futureTask);
//        System.out.println(futureTask.isCancelled());
//        try {
//            System.out.println(futureTask.get());
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        } catch (ExecutionException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println(futureTask.isCancelled());
//        System.out.println(Thread.currentThread());
//    }
//
//    public static class MyThread extends Thread {
//
//        @Override
//        public void run() {
//            System.out.println("className:" + this.getClass().getName() + ", threadName:" + this.getName());
//        }
//    }
//
//    public static class MyThread2 implements Runnable {
//
//        @Override
//        public void run() {
//            System.out.println("className:" + this.getClass().getName());
//        }
//    }
//
//    public static class Task implements Callable<Integer> {
//
//        @Override
//        public Integer call() throws Exception {
//            Thread.sleep(1000);
//            return 2;
//        }
//    }
//}
