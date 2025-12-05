package com.example.demo.bean;

import org.springframework.beans.factory.DisposableBean;

/**
 * @Author: zhoujing
 * @Date: 2025/11/17 17:49
 * @Description:
 */
public class BeanA implements DisposableBean {

    public BeanA() {
        System.out.println("BeanA Initialized");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("BeanA Destroyed");
    }
}
