package com.example.demo.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.time.Duration;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @Author: zhoujing
 * @Date: 2024/2/2 13:30
 * @Description:
 */
public class CacheUtil {

    public static <K, V>LoadingCache<K, V> buildAsyncReloadingCache(Duration duration, CacheLoader<K, V> loader) {
        Executor executor = Executors.newCachedThreadPool();
        return CacheBuilder.newBuilder()
                // 定时回收：缓存项在给定时间内没有被写访问（创建或覆盖），则回收
                .refreshAfterWrite(duration)
                // 开启统计功能
                .recordStats()
                // 异步刷新缓存
                .build(CacheLoader.asyncReloading(loader, executor));
    }
}
