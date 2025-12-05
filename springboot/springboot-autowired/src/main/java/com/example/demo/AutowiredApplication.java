package com.example.demo;

import com.example.demo.controller.MyController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @Author: zhoujing
 * @Date: 2025/9/19 14:25
 * @Description:
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class AutowiredApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(AutowiredApplication.class, args);
        MyController controller = context.getBean(MyController.class);
        controller.showService();
    }
}
