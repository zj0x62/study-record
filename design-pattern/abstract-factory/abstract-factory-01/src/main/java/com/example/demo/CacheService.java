package com.example.demo;

import java.util.concurrent.TimeUnit;

/**
 * @Author: zhoujing
 * @Date: 2025/2/5 10:29
 * @Description:
 */
public interface CacheService {

    String get(final String key, int redisType);

    void set(String key, String value, int redisType);

    void set(String key, String value, long timeout, TimeUnit timeUnit, int redisType);

    void del(String key, int redisType);
}
