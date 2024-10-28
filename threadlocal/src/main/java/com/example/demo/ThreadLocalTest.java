package com.example.demo;

import org.junit.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: zhoujing
 * @Date: 2024/8/7 16:14
 * @Description:
 */
@SpringBootTest(classes = ThreadLocalTest.class)
@SpringBootApplication
public class ThreadLocalTest {

    private static final ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 0);

    private static int num = 0;

    @Test
    public void test() {
        Thread[] threads = new Thread[5];

        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(() -> {
                threadLocal.set(num++);
                System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());
            }, "Thread-" + i);
        }

        for (Thread thread : threads) {
            thread.start();
        }
    }
}
