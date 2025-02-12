package com.example.demo;

import java.util.concurrent.Callable;

/**
 * @Author: zhoujing
 * @Date: 2025/2/6 17:26
 * @Description:
 */
public class Task implements Callable<Long> {

    private long num;

    public Task(long num) {
        this.num = num;
    }

    @Override
    public Long call() throws Exception {
        long r = 0;
        for (int n = 1; n <= this.num; n++) {
            r = r + n;
        }
        System.out.println("result: " + r);
        return r;
    }
}
