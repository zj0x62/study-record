package com.example.demo.thread;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;

/**
 * @Author: zhoujing
 * @Date: 2023/10/9 13:48
 * @Description:
 */
public class RateLimit {

    private static final int REQUEST_PER_SECOND = 20;
    private static final RateLimiter RATE_LIMITER = RateLimiter.create(REQUEST_PER_SECOND);


    public static void main(String[] args) throws Exception {
//        // 创建一个每秒生成 2 个令牌的 RateLimiter
//        RateLimiter rateLimiter = RateLimiter.create(2.0);
//
//        for (int i = 0; i < 100; i++) {
//            // 尝试获取一个令牌，如果获取成功就执行操作
//            if (rateLimiter.tryAcquire()) {
//                System.out.println("执行操作: " + i);
//            } else {
//                System.out.println("限流，无法执行操作 " + i);
//            }
//        }
        demo();
    }

    private static void demo() {
        // 模拟突发请求
        simulateBurstRequests();

        // 处理其他请求
        for (int i = 0; i < 100; i++) {
            handleRequest(i);
        }
    }

    private static void handleRequest(int requestId) {
//        double waitTime = RATE_LIMITER.acquire(); // 获取令牌，等待时间取决于速率
//
//        System.out.println("Request " + requestId + " handled. Wait time: " + waitTime + " seconds.");
        if (!RATE_LIMITER.tryAcquire(500, TimeUnit.MILLISECONDS)) {
            System.out.println("获取令牌失败：requestId = " + requestId);
        }
        System.out.println("获取令牌成功：requestId = " + requestId);
    }

    private static void simulateBurstRequests() {
        // 模拟突发请求，一次性发出一定数量的请求
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    handleRequest(-1); // 使用负数 ID 表示突发请求
                }
            }).start();
        }
    }
}
