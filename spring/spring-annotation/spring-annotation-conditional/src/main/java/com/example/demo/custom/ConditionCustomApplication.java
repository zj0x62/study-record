package com.example.demo.custom;

import com.example.demo.custom.config.MyCustomConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author: zhoujing
 * @Date: 2025/11/14 13:59
 * @Description:
 */
public class ConditionCustomApplication {

    public static void main(String[] args) {
        System.setProperty("enable.custom", "false");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyCustomConfiguration.class);
        for (String beanDefinitionName : context.getBeanDefinitionNames()) {
            System.out.println("beanDefinitionName = " + beanDefinitionName);
        }
    }
}
