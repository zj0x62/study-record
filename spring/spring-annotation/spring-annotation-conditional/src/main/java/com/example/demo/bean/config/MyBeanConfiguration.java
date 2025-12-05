package com.example.demo.bean.config;

import com.example.demo.bean.condition.BeanPropertyCondition;
import com.example.demo.bean.entity.User1;
import com.example.demo.bean.entity.User2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: zhoujing
 * @Date: 2025/11/14 13:42
 * @Description:
 */
@Configuration
public class MyBeanConfiguration {

    @Bean
    @Conditional(BeanPropertyCondition.class)
    public User1 user1() {
        return new User1();
    }

    @Bean
    public User2 user2() {
        return new User2();
    }
}
