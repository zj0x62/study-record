package com.example.demo.reflect;

import java.lang.reflect.Field;

/**
 * @Author: zhoujing
 * @Date: 2023/11/27 13:54
 * @Description:
 */
public class Test {

    private static class inner {
    }

    public static void main(String[] args) throws Throwable {
//        demo();
//        demo2();
//        demo3();
    }

    private static void demo() throws Throwable {
        // 获取Class对象的三种方式
        System.out.println("根据类名：\t" + User.class);
        System.out.println("根据对象：\t" + new User().getClass());
        System.out.println("根据全限定类名：\t" + Class.forName("com.example.demo.reflect.User"));
        // 常用方法
        Class<User> userClass = User.class;
        System.out.println("获取全限定类名：\t" + userClass.getName());
        System.out.println("获取类名：\t" + userClass.getSimpleName());
        System.out.println("实例化：\t" + userClass.newInstance());
    }

    /**
     *  getName:类的全限定名，jvm中Class的表示，可以用于动态加载Class对象，例如Class.forName
     *  getSimpleName:只获取类名
     *  getCanonicalName:返回更容易理解的表示，主要用于输出（toString）或log打印，大多数情况下和getName一样，但是在内部类、数组等类型的表示形式就不同了
     */
    private static void demo2() throws Throwable {
        Class<Dog> dog = Dog.class;
        // 类名打印
        System.out.println(dog.getName());
        System.out.println(dog.getSimpleName());
        System.out.println(dog.getCanonicalName());
        // 接口
        System.out.println(dog.isInterface());
        for (Class<?> anInterface : dog.getInterfaces()) {
            System.out.println(anInterface);
        }
        // 父类
        System.out.println(dog.getSuperclass());
        // 创建对象
        Dog d = dog.newInstance();
        // 字段
        for (Field field : dog.getFields()) {
            System.out.println(field.getName());
        }
        System.out.println("-------");
        for (Field field : dog.getDeclaredFields()) {
            System.out.println(field.getName());
        }
    }

    private static void demo3() throws Throwable {
        // 普通类
        System.out.println(Test.class.getName());
        System.out.println(Test.class.getSimpleName());
        System.out.println(Test.class.getCanonicalName());
        // 内部类
        System.out.println(inner.class.getName());
        System.out.println(inner.class.getSimpleName());
        System.out.println(inner.class.getCanonicalName());
        // 不能用getCanonicalName去加载类对象，必须用getName
//        Class.forName(inner.class.getCanonicalName()); //报错
        Class.forName(inner.class.getName());
    }
}
