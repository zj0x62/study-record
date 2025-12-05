package com.example.demo.custom.config;

import com.example.demo.custom.annotation.ConditionalOnCustomActive;
import com.example.demo.custom.entity.User5;
import com.example.demo.custom.entity.User6;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: zhoujing
 * @Date: 2025/11/14 13:58
 * @Description:
 */
@Configuration
@ConditionalOnCustomActive
public class MyCustomConfiguration {

    @Bean
    public User5 user5() {
        return new User5();
    }

    @Bean
    public User6 user6() {
        return new User6();
    }
}
