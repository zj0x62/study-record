package com.example.demo.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @Author: zhoujing
 * @Date: 2025/12/8 9:58
 * @Description:
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {

    String value() default "";
}
