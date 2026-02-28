package com.example.demo.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @Author: zhoujing
 * @Date: 2025/12/8 9:59
 * @Description:
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface MyClassAnnotation {

    String value() default "";
}
