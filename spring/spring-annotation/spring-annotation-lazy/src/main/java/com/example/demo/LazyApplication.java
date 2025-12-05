package com.example.demo;

import com.example.demo.config.MyConfiguration;
import com.example.demo.service.MyService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author: zhoujing
 * @Date: 2025/11/24 13:58
 * @Description:
 */
public class LazyApplication {

    public static void main(String[] args) {
        System.out.println("启动 Spring ApplicationContext...");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfiguration.class);
        System.out.println("启动完成 Spring ApplicationContext...");

        System.out.println("获取MyService...");
        MyService myService = context.getBean(MyService.class);

        System.out.println("调用show方法...");
        myService.show();
    }
}
