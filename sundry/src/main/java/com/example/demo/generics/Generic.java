package com.example.demo.generics;

/**
 * @Author: zhoujing
 * @Date: 2023/11/22 10:34
 * @Description:
 */
public class Generic {

    public <T> T getObject(Class<T> c) throws Exception{
        // 创建泛型对象
        T t = c.newInstance();
        return t;
    }
}
