package com.example.demo;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: zhoujing
 * @Date: 2024/7/23 14:30
 * @Description:
 */
@Configuration
@EnableConfigurationProperties(TestConfigurationProperties.class)
public class TestAutoConfiguration {

    private TestConfigurationProperties testConfigurationProperties;

    public TestAutoConfiguration(TestConfigurationProperties testConfigurationProperties) {
        this.testConfigurationProperties = testConfigurationProperties;
    }

    @Bean
    public User user() {
        User user = new User();
        user.setName(testConfigurationProperties.getName());
        user.setAge(testConfigurationProperties.getAge());
        return user;
    }
}
