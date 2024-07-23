package com.example.demo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: zhoujing
 * @Date: 2024/7/23 14:29
 * @Description:
 */
@Data
@ConfigurationProperties(prefix = "properties")
public class TestConfigurationProperties {

    private String name;

    private int age;
}
