package com.example.demo.configuration;

import com.example.demo.configuration.config.MyConfigConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author: zhoujing
 * @Date: 2025/11/14 13:51
 * @Description:
 */
public class ConditionConfigurationApplication {

    public static void main(String[] args) {
        System.setProperty("enable.config", "false");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfigConfiguration.class);
        for (String beanDefinitionName : context.getBeanDefinitionNames()) {
            System.out.println("beanDefinitionName = " + beanDefinitionName);
        }
    }
}
