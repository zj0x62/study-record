package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: zhoujing
 * @Date: 2024/7/22 10:03
 * @Description:
 */
@Configuration
public class BeanConfig {

    @Bean
    public Customizer customizer() {
        return new Bean2();
    }

}
