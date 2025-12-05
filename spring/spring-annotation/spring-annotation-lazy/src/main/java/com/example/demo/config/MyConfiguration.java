package com.example.demo.config;

import com.example.demo.bean.MyBean;
import com.example.demo.service.MyService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * @Author: zhoujing
 * @Date: 2025/11/24 13:56
 * @Description:
 */
@Configuration
public class MyConfiguration {

    @Bean
    @Lazy
    public MyBean myBean() {
        System.out.println("MyBean 初始化了！");
        return new MyBean();
    }

    @Bean
    public MyService myService() {
        return new MyService();
    }
}
