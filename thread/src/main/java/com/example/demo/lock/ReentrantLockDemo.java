package com.example.demo.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: zhoujing
 * @Date: 2025/2/18 16:33
 * @Description: 可重入锁
 *               可指定是否为公平锁（默认非公平）
 */
public class ReentrantLockDemo {

    private final Lock lock = new ReentrantLock();
    private int count;

    public void add(int n) {
        lock.lock();
        try {
            count += n;
        } finally {
            lock.unlock();
        }
    }

    public void sub(int n) throws InterruptedException {
        if (lock.tryLock(1, TimeUnit.SECONDS)) {
            try {
                count -= n;
            } finally {
                lock.unlock();
            }
        }
    }
}
