package com.example.demo;

import com.example.demo.config.MyConfiguration;
import com.example.demo.controller.MyController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author: zhoujing
 * @Date: 2025/9/19 14:09
 * @Description:
 */
public class AutowiredApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfiguration.class);
        MyController controller = context.getBean(MyController.class);
        controller.showService();
    }
}
