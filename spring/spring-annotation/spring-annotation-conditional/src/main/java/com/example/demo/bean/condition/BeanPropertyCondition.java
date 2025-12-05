package com.example.demo.bean.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @Author: zhoujing
 * @Date: 2025/11/14 13:39
 * @Description:
 */
public class BeanPropertyCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return "true".equals(context.getEnvironment().getProperty("enable.bean"));
    }
}
