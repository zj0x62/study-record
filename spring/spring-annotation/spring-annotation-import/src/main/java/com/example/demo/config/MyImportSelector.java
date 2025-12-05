package com.example.demo.config;

import com.example.demo.bean.MyBeanA;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Author: zhoujing
 * @Date: 2025/11/19 18:12
 * @Description:
 */
public class MyImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{MyBeanA.class.getName()};
    }
}
