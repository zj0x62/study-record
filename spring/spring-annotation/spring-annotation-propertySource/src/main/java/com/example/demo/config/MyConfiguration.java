package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @Author: zhoujing
 * @Date: 2025/11/25 10:47
 * @Description:
 */
@Configuration
@PropertySource("classpath:my-application.yml")
public class MyConfiguration {
}
