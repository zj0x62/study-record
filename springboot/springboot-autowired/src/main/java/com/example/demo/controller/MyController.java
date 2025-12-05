package com.example.demo.controller;

import com.example.demo.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @Author: zhoujing
 * @Date: 2025/9/19 14:11
 * @Description:
 */
@Controller
public class MyController {

    @Autowired
    private MyService myService;

    public void showService() {
        System.out.println("myService = " + myService);
    }
}
