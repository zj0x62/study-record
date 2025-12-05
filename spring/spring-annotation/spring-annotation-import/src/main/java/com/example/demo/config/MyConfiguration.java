package com.example.demo.config;

import com.example.demo.bean.MyBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Author: zhoujing
 * @Date: 2025/11/19 18:09
 * @Description:
 */
@Configuration
@Import({MyBean.class, MyImportSelector.class, MyDeferredImportSelector.class, MyImportBeanDefinitionRegistrar.class})
public class MyConfiguration {
}
