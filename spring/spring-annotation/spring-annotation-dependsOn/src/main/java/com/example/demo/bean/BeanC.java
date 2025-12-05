package com.example.demo.bean;

import org.springframework.beans.factory.DisposableBean;

/**
 * @Author: zhoujing
 * @Date: 2025/11/17 17:52
 * @Description:
 */
public class BeanC implements DisposableBean {

    public BeanC() {
        System.out.println("BeanC Initialized");
    }


    @Override
    public void destroy() throws Exception {
        System.out.println("BeanC Destroyed");
    }
}
