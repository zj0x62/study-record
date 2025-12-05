package com.example.demo;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.InputStream;

/**
 * @Author: zhoujing
 * @Date: 2025/12/1 19:00
 * @Description:
 */
public class ClassPathResourceDemo {

    public static void main(String[] args) throws Exception {
        String path = "application.properties";
        Resource resource = new ClassPathResource(path);
        try(InputStream is = resource.getInputStream()) {
            // 读取和处理资源内容
            System.out.println(new String(is.readAllBytes()));
        }
    }
}
