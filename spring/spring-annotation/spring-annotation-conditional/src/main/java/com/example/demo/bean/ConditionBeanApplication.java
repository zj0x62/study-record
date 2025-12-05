package com.example.demo.bean;

import com.example.demo.bean.config.MyBeanConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author: zhoujing
 * @Date: 2025/11/14 13:44
 * @Description:
 */
public class ConditionBeanApplication {

    public static void main(String[] args) {
        System.setProperty("enable.bean", "false");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyBeanConfiguration.class);
        for (String beanDefinitionName : context.getBeanDefinitionNames()) {
            System.out.println("beanDefinitionName = " + beanDefinitionName);
        }
    }
}
