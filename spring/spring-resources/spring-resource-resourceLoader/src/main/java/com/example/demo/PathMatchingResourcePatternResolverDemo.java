package com.example.demo;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * @Author: zhoujing
 * @Date: 2025/12/3 16:47
 * @Description:
 */
public class PathMatchingResourcePatternResolverDemo {

    public static void main(String[] args) throws Exception {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        // 加载所有匹配的类路径资源
        Resource[] resources = resolver.getResources("classpath*:*.properties");
        for (Resource resource : resources) {
            System.out.println("Classpath = " + resource.getFilename());
        }

        // 加载文件系统中的所有匹配资源
        Resource[] fileResources = resolver.getResources("file:/IDEAProjects/study-record/spring/spring-resources/spring-resource-resourceLoader/*.txt");
        for (Resource resource : fileResources) {
            System.out.println("File = " + resource.getFilename());
        }
    }
}
