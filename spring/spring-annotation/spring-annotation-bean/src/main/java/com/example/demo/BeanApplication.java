package com.example.demo;

import com.example.demo.bean.MyBean;
import com.example.demo.config.MyConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author: zhoujing
 * @Date: 2025/9/26 13:30
 * @Description:
 */
public class BeanApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfiguration.class);
        System.out.println(context.getBean(MyBean.class));
        context.close();
    }
}
