package com.example.demo;

import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author: zhoujing
 * @Date: 2025/12/11 15:39
 * @Description:
 */
public class AnnotationMetadataDemoByASM {

    public static void main(String[] args) throws Exception {
        // 创建MetadataReaderFactory
        SimpleMetadataReaderFactory readerFactory = new SimpleMetadataReaderFactory();
        // 获取MetadataReader
        MetadataReader metadataReader = readerFactory.getMetadataReader("com.example.demo.bean.myBean");

        // 获取AnnotationMetadata
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();

        System.out.println("AnnotationMetadata impl class is: " + annotationMetadata.getClass());

        // 检查MyBean类是否被@Component注解标记
        boolean isComponent = annotationMetadata.hasAnnotation(Component.class.getName());
        System.out.println("MyBean is a @Component: " + isComponent);

        // 获取MyBean类上的注解属性
        if (isComponent) {
            Map<String, Object> annotationAttributes = annotationMetadata.getAnnotationAttributes(Component.class.getName());
            System.out.println("@Component value is: " + annotationAttributes.get("value"));
        }
    }
}
