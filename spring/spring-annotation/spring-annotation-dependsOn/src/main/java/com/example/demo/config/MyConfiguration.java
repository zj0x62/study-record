package com.example.demo.config;

import com.example.demo.bean.BeanA;
import com.example.demo.bean.BeanB;
import com.example.demo.bean.BeanC;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * @Author: zhoujing
 * @Date: 2025/11/17 17:53
 * @Description:
 */
@Configuration
public class MyConfiguration {

    @Bean
    @DependsOn("beanB")
    public BeanA beanA() {
        return new BeanA();
    }

    @Bean
    @DependsOn("beanC")
    public BeanB beanB() {
        return new BeanB();
    }

    @Bean
    public BeanC beanC() {
        return new BeanC();
    }
}
