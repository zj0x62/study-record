package com.example.demo.annotation;

import org.apache.xmlbeans.impl.xb.xsdschema.Annotated;

import java.io.FileNotFoundException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhoujing
 * @Date: 2022/12/1 10:47
 * @Description:
 */
public class TestMethodAnnotation {

    @Override
    @MyMethodAnnotation(title = "toStringMethod", description = "override toString method")
    public String toString() {
        return "Override toString method";
    }

    @Deprecated
    @MyMethodAnnotation(title = "old static method", description = "deprecated old static method")
    public static void oldMethod() {
        System.out.println("old method, don't use it.");
    }

    @SuppressWarnings({"unchecked", "deprecation"})
    @MyMethodAnnotation(title = "test method", description = "suppress warning static method")
    public static void genericsTest() throws FileNotFoundException {
        List<String> l = new ArrayList<>();
        l.add("abc");
        oldMethod();
    }

    public static void main(String[] args) {
        try {
            //获取所有methods
            Method[] methods = TestMethodAnnotation.class.getClassLoader()
                    .loadClass("com.example.demo.annotation.TestMethodAnnotation")
                    .getMethods();

            //遍历
            for (Method method : methods) {
                //方法上是否有MyMethodAnnotation注解
                if (method.isAnnotationPresent(MyMethodAnnotation.class)) {
                    try {
                        //获取并遍历方法上的所有注解
                        for (Annotation annotation : method.getDeclaredAnnotations()) {
                            System.out.println("Annotation in method '" + method + "' : " + annotation);
                        }

                        //获取MyMethodAnnotation对象信息
                        MyMethodAnnotation methodAnnotation = method.getAnnotation(MyMethodAnnotation.class);

                        System.out.println(methodAnnotation.title());
                    } catch (Throwable e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (SecurityException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
