package com.example.demo;

import com.example.demo.bean.MyBean;
import com.example.demo.config.MyConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author: zhoujing
 * @Date: 2025/11/13 18:07
 * @Description:
 */
public class ConfigurationApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfiguration.class);
        MyConfiguration configuration = context.getBean(MyConfiguration.class);
        MyBean bean = context.getBean(MyBean.class);

        System.out.println(configuration.myBean());
        System.out.println(configuration.myBean());
        System.out.println(bean);
        System.out.println(configuration.myBean());
    }
}
