package com.example.leaf;

import com.example.leaf.common.Result;

/**
 * @Author: zhoujing
 * @Date: 2024/7/18 9:54
 * @Description:
 */
public interface IDGen {

    Result get(String key);

    boolean init();
}
