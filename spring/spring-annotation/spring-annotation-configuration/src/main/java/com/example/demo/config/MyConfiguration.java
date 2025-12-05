package com.example.demo.config;

import com.example.demo.bean.MyBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: zhoujing
 * @Date: 2025/11/13 18:06
 * @Description:
 */
@Configuration
// 将proxyBeanMethods设置为false时，代理行为将被禁用。这意味着，如果我们在配置类内部多次调用同一个@Bean方法，每次都会创建一个新的实例。
//@Configuration(proxyBeanMethods = false)
public class MyConfiguration {

    @Bean
    public MyBean myBean() {
        return new MyBean();
    }
}
