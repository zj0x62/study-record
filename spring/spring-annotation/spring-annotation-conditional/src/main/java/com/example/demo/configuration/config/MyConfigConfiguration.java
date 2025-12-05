package com.example.demo.configuration.config;

import com.example.demo.configuration.condition.ConfigPropertyCondition;
import com.example.demo.configuration.entity.User3;
import com.example.demo.configuration.entity.User4;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: zhoujing
 * @Date: 2025/11/14 13:50
 * @Description:
 */
@Configuration
@Conditional(ConfigPropertyCondition.class)
public class MyConfigConfiguration {

    @Bean
    public User3 user3() {
        return new User3();
    }

    @Bean
    public User4 user4() {
        return new User4();
    }
}
