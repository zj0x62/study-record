package com.example.demo.config;

import com.example.demo.service.MyService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @Author: zhoujing
 * @Date: 2025/11/25 18:41
 * @Description:
 */
@Configuration
@PropertySource("classpath:application.properties")
public class MyConfiguration {

    @Bean
    public MyService myService() {
        return new MyService();
    }
}
