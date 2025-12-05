package com.example.demo.config;

import com.example.demo.service.AdminService;
import com.example.demo.special.SpecialComponent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * @Author: zhoujing
 * @Date: 2025/11/11 18:15
 * @Description:
 */
@Configuration
@ComponentScan(basePackages = "com.example.demo",
        includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SpecialComponent.class),
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = AdminService.class)
)
public class MyConfiguration {
}
