package com.example.demo;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.InputStream;

/**
 * @Author: zhoujing
 * @Date: 2025/12/3 14:08
 * @Description:
 */
public class UrlResourceDemo {

    public static void main(String[] args) throws Exception{
        Resource resource = new UrlResource("https://dist.apache.org/repos/dist/test/test.txt");
        try(InputStream is = resource.getInputStream()) {
            System.out.println(new String(is.readAllBytes()));
        }
    }
}
