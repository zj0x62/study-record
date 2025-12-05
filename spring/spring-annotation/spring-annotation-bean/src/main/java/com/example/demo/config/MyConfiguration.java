package com.example.demo.config;

import com.example.demo.bean.MyBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: zhoujing
 * @Date: 2025/9/26 13:28
 * @Description:
 */
@Configuration
public class MyConfiguration {

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public MyBean myBean() {
        return new MyBean();
    }
}
