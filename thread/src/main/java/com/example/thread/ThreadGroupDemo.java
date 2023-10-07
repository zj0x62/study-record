package com.example.thread;

/**
 * @Author: zhoujing
 * @Date: 2023/10/7 10:37
 * @Description: ThreadGroup 线程组是一个树状结构，每个线程组下面可以有多个线程或线程组。线程组可以起到统一控制线程的优先级和检查线程的权限的作用。
 *
 * ThreadGroup是一个标准的向下引用的树状结构，这样设计的原因是 防止“上级”线程被”下级“线程引用而无法有效地被GC回收。
 * RootThreadGroup
 * ├── ParentThreadGroup
 * │   ├── Thread 1
 * │   ├── Thread 2
 * │   └── ChildThreadGroup
 * │       ├── Thread 3
 * │       └── Thread 4
 * └── OtherThread
 *
 */
public class ThreadGroupDemo {

    public static void main(String[] args) {
//        demo();
        demo2();
    }

    private static void demo() {
        Thread testThread = new Thread(() -> {
            System.out.println("testThread当前线程组名字：" + Thread.currentThread().getThreadGroup().getName());
            System.out.println("testThread当前线程名字：" + Thread.currentThread().getName());
        });

        testThread.start();
        System.out.println("执行main所在线程的线程组名字：" + Thread.currentThread().getThreadGroup().getName());
        System.out.println("执行main方法线程名字：" + Thread.currentThread().getName());
    }

    private static void demo2() {
        ThreadGroup threadGroup = new ThreadGroup("group1") {
            // 在线程成员抛出unchecked exception时，会执行此方法
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println(t.getName() + ": " + e.getMessage());
            }
        };

        Thread thread = new Thread(threadGroup, new Runnable() {
            @Override
            public void run() {
                // 抛出unchecked异常
                throw new RuntimeException("测试异常");
            }
        });

        thread.start();
    }
}
