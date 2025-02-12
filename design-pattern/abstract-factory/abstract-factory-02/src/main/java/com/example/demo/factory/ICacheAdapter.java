package com.example.demo.factory;

import java.util.concurrent.TimeUnit;

/**
 * @Author: zhoujing
 * @Date: 2025/2/5 10:36
 * @Description:
 */
public interface ICacheAdapter {

    String get(String key);

    void set(String key, String value);

    void set(String key, String value, long timeout, TimeUnit timeUnit);

    void del(String key);
}
