package com.example.demo.service;

import com.example.demo.bean.MyBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

/**
 * @Author: zhoujing
 * @Date: 2025/11/24 13:55
 * @Description:
 */
public class MyService {

    @Lazy
    @Autowired
    private MyBean myBean;

    public void show() {
        System.out.println("MyBean Class = " + myBean.getClass());
        myBean.show();
    }
}
