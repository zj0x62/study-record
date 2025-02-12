package com.example.demo.factory.impl;

import com.example.demo.factory.ICacheAdapter;
import com.example.demo.matter.IIR;

import java.util.concurrent.TimeUnit;

/**
 * @Author: zhoujing
 * @Date: 2025/2/5 10:38
 * @Description:
 */
public class IIRCacheAdapter implements ICacheAdapter {

    private IIR iir = new IIR();

    @Override
    public String get(String key) {
        return iir.get(key);
    }

    @Override
    public void set(String key, String value) {
        iir.set(key, value);
    }

    @Override
    public void set(String key, String value, long timeout, TimeUnit timeUnit) {
        iir.setExpire(key, value, timeout, timeUnit);
    }

    @Override
    public void del(String key) {
        iir.del(key);
    }
}
