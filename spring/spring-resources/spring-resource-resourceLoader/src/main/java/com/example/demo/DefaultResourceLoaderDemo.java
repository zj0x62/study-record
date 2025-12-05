package com.example.demo;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

/**
 * @Author: zhoujing
 * @Date: 2025/12/3 16:43
 * @Description:
 */
public class DefaultResourceLoaderDemo {

    public static void main(String[] args) {
        DefaultResourceLoader loader = new DefaultResourceLoader();

        // 从类路径加载资源
        Resource classpathResource = loader.getResource("classpath:application.properties");
        System.out.println("Classpath Exists = " + classpathResource.exists());

        // 加载文件系统中的资源
        Resource fileResource = loader.getResource("");
        System.out.println("File Exists = " + fileResource.exists());
    }
}
