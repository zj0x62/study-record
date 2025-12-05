package com.example.demo;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;

import java.io.InputStream;

/**
 * @Author: zhoujing
 * @Date: 2025/12/1 18:28
 * @Description:
 */
public class ByteArrayResourceDemo {

    public static void main(String[] args) throws Exception {
        byte[] data = "hello world".getBytes();
        Resource resource = new ByteArrayResource(data);
        try(InputStream is = resource.getInputStream()) {
            // 读取和处理资源内容
            System.out.println(new String(is.readAllBytes()));
        }
    }
}
