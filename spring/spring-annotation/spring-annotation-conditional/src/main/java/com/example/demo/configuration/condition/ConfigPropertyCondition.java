package com.example.demo.configuration.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @Author: zhoujing
 * @Date: 2025/11/14 13:48
 * @Description:
 */
public class ConfigPropertyCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return "true".equals(System.getProperty("enable.config"));
    }
}
