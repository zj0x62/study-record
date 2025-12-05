package com.example.demo.custom.annotation;

import com.example.demo.custom.condition.CustomActiveCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: zhoujing
 * @Date: 2025/11/14 13:54
 * @Description:
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Conditional(CustomActiveCondition.class)
public @interface ConditionalOnCustomActive {

}
