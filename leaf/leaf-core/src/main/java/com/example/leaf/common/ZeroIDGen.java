package com.example.leaf.common;

import com.example.leaf.IDGen;

/**
 * @Author: zhoujing
 * @Date: 2024/7/18 9:55
 * @Description:
 */
public class ZeroIDGen implements IDGen {
    @Override
    public Result get(String key) {
        return new Result(0, Status.SUCCESS);
    }

    @Override
    public boolean init() {
        return true;
    }
}
