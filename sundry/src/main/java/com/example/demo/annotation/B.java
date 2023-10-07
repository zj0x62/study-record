package com.example.demo.annotation;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhoujing
 * @Date: 2022/12/1 9:36
 * @Description:
 */
public class B extends A{

    /**
     * 重写父类的test方法
     */
    @Override
    public void test() {

    }

    /**
     * 被弃用的方法
     */
    @Deprecated
    public void oldMethod() {

    }

    /**
     * 忽略警告
     */
    @SuppressWarnings("rawtypes")
    public List processList() {
        List list = new ArrayList();
        return list;
    }
}
