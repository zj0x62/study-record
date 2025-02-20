package com.example.demo;

import java.util.UUID;
import java.util.concurrent.Semaphore;

/**
 * @Author: zhoujing
 * @Date: 2025/2/18 17:23
 * @Description: 信号量
 */
public class SemaphoreDemo {

    // 任意时刻仅允许最多3个线程获取许可
    private final Semaphore semaphore = new Semaphore(3);

    public String access() throws Exception {
        // 如果超过了许可数量，其他线程将在此等待
        semaphore.acquire();
        try {
            return UUID.randomUUID().toString();
        } finally {
            semaphore.release();
        }
    }
}
