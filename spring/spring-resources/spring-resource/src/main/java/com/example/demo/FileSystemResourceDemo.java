package com.example.demo;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;

/**
 * @Author: zhoujing
 * @Date: 2025/12/3 14:02
 * @Description:
 */
public class FileSystemResourceDemo {

    public static void main(String[] args) throws Exception {
        String path = "D:\\IDEAProjects\\study-record\\spring\\spring-resources\\spring-resource\\myfile.txt";
        Resource resource = new FileSystemResource(path);
        try(InputStream is = resource.getInputStream()) {
            // 读取和处理资源内容
            System.out.println(new String(is.readAllBytes()));
        }

        boolean exists = resource.exists();
        System.out.println("exists: " + exists);
        boolean open = resource.isOpen();
        System.out.println("open: " + open);
        boolean file = resource.isFile();
        System.out.println("file: " + file);
        URL url = resource.getURL();
        System.out.println("url: " + url);
        URI uri = resource.getURI();
        System.out.println("uri: " + uri);
        long contentLength = resource.contentLength();
        System.out.println("contentLength: " + contentLength);
        long lastModified = resource.lastModified();
        System.out.println("lastModified: " + lastModified);
        String filename = resource.getFilename();
        System.out.println("filename: " + filename);
        String description = resource.getDescription();
        System.out.println("description: " + description);
    }
}
