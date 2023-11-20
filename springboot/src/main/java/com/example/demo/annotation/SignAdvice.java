package com.example.demo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: zhoujing
 * @Date: 2023/10/11 10:50
 * @Description: 自动签名
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface SignAdvice {

    /**
     * 自定义签名key
     * 如果不指定，则使用系统默认key
     * @return
     */
    String signKey() default "";
}
