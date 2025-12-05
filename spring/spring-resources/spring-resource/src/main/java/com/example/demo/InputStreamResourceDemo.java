package com.example.demo;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @Author: zhoujing
 * @Date: 2025/12/3 14:06
 * @Description:
 */
public class InputStreamResourceDemo {

    public static void main(String[] args) throws Exception {
        InputStream isSource = new ByteArrayInputStream("hello world".getBytes());
        Resource resource = new InputStreamResource(isSource);
        try(InputStream is = resource.getInputStream()) {
            System.out.println(new String(is.readAllBytes()));
        }
    }
}
