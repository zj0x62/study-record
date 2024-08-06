package com.example.demo;

import org.apache.ibatis.reflection.TypeParameterResolver;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Type;

/**
 * @Author: zhoujing
 * @Date: 2024/7/29 14:31
 * @Description:
 */
@SpringBootApplication
public class MybatisReflectionApp {

    public static void main(String[] args) throws Exception {
        Type type1 = TypeParameterResolver.resolveReturnType(User.class.getMethod("getInfo"), User.class);
        System.out.println("User类中getInfo方法的输出结果类型:\n" + type1);

        Type type2 = TypeParameterResolver.resolveReturnType(Student.class.getMethod("getInfo"), Student.class);
        System.out.println("Student类中getInfo方法的输出结果类型:\n" + type2);
    }
}
