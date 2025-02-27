package com.example.demo;

import java.util.concurrent.Callable;

/**
 * @Author: zhoujing
 * @Date: 2025/2/6 17:30
 * @Description:
 */
public class RunnableAdapter implements Runnable {

    // 引用待转换接口
    private Callable<?> callable;

    public RunnableAdapter (Callable<?> callable) {
        this.callable = callable;
    }

    // 实现指定接口
    @Override
    public void run() {
        // 将指定接口调用委托给转换接口调用
        try {
            callable.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
