package com.example.demo;

import com.example.demo.config.MyConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author: zhoujing
 * @Date: 2025/11/25 10:48
 * @Description:
 */
public class PropertySourceApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfiguration.class);
        System.out.println("apiVersion = " + context.getEnvironment().getProperty("apiVersion"));
        System.out.println("kind = " + context.getEnvironment().getProperty("kind"));
    }
}
