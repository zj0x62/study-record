package com.example.demo.config;

import com.example.demo.bean.MyBeanB;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Author: zhoujing
 * @Date: 2025/11/19 18:09
 * @Description:
 */
public class MyDeferredImportSelector implements DeferredImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{MyBeanB.class.getName()};
    }
}
