package com.example.demo.proxy;

/**
 * @Author: zhoujing
 * @Date: 2025/2/13 16:46
 * @Description:
 */
public class RealSubject implements Subject {

    @Override
    public void request() {
        System.out.println("From real subject.");
    }
}
