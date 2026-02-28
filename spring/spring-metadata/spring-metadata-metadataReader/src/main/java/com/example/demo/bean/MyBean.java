package com.example.demo.bean;

import com.example.demo.annotation.MyAnnotation;
import com.example.demo.annotation.MyClassAnnotation;

import java.io.Serializable;

/**
 * @Author: zhoujing
 * @Date: 2025/12/8 10:00
 * @Description:
 */
@MyClassAnnotation
public final class MyBean extends MyAbstract implements Serializable {

    public String key;

    public String value;

    @MyAnnotation
    public static void myMethod1() {
        // 方法1的实现
    }

    @MyAnnotation
    public String myMethod2() {
        return "hello world";
    }

    public void myMethod3() {
        // 方法3的实现
    }

    public static class MyInnerClass {
        // 内部类的定义
    }
}
