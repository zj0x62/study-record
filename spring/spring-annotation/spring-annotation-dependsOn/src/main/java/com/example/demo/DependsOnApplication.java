package com.example.demo;

import com.example.demo.config.MyConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author: zhoujing
 * @Date: 2025/11/17 17:55
 * @Description:
 */
public class DependsOnApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfiguration.class);
        context.close();
    }
}
