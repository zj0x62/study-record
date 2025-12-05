package com.example.demo.bean;

import org.springframework.beans.factory.DisposableBean;

/**
 * @Author: zhoujing
 * @Date: 2025/11/17 17:51
 * @Description:
 */
public class BeanB implements DisposableBean {

    public BeanB() {
        System.out.println("BeanB Initialized");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("BeanB Destroyed");
    }
}
