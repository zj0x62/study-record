package com.example.demo.lock;

import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author: zhoujing
 * @Date: 2025/2/18 16:43
 * @Description: 分离读锁和写锁：
 *                  读锁（共享锁）：允许多个线程同时读。
 *                  写锁（独占锁）：同一时间只能有一个线程写。
 *               潜在的问题：如果有线程正在读，写线程需要等待读线程释放锁后才能获取写锁，即读的过程中不允许写，这是一种悲观的读锁。
 *               使用ReadWriteLock可以提高读取效率：
 *               ReadWriteLock只允许一个线程写入；
 *               ReadWriteLock允许多个线程在没有写入时同时读取；
 *               ReadWriteLock适合读多写少的场景。
 */
public class ReadWriteLockDemo {

    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final Lock rLock = rwLock.readLock();
    private final Lock wLock = rwLock.writeLock();
    private int[] counts = new int[10];

    public void inc(int index) {
        wLock.lock(); // 加写锁
        try {
            counts[index] += 1;
        } finally {
            wLock.unlock(); // 释放写锁
        }
    }

    public int[] get() {
        rLock.lock(); // 加读锁
        try {
            return Arrays.copyOf(counts, counts.length);
        } finally {
            rLock.unlock(); // 释放读锁
        }
    }
}
