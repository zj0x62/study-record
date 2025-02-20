package com.example.demo;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Author: zhoujing
 * @Date: 2025/2/13 14:52
 * @Description:
 */
public class Main {

    public static void main(String[] args) throws Exception {
        // 获取字段值
//        getField();
        // 设置字段值
//        setField();
        // 获取方法
//        getMethod();
        // 调用方法
//        invokeMethod();
        // 多态
//        polymorphic();
        // 调用构造方法
        constructor();
    }

    private static void getField() throws Exception {
        Class<Student> stdClass = Student.class;
        // 获取public字段"score"
        Field score = stdClass.getField("score");
        System.out.println(score);
        // 获取继承的public字段"name"
        Field name = stdClass.getField("name");
        System.out.println(name);
        // 获取private字段"grade"
        Field grade = stdClass.getDeclaredField("grade");
        System.out.println(grade);
    }

    private static void setField() throws Exception {
        Person p = new Person("Xiao Ming");
        System.out.println(p.getName());
        Class c = p.getClass();
        Field field = c.getDeclaredField("name");
        field.setAccessible(true);
        field.set(p, "Xiao Hong");
        System.out.println(p.getName());
    }

    private static void getMethod() throws Exception {
        Class stdClass = Student.class;
        Method getScore = stdClass.getMethod("getScore", String.class);
        System.out.println(getScore);
        Method getName = stdClass.getMethod("getName");
        System.out.println(getName);
        Method getGrade = stdClass.getDeclaredMethod("getGrade", int.class);
        System.out.println(getGrade);
    }

    private static void invokeMethod() throws Exception {
        Student student = new Student();
        Class stdClass = student.getClass();
        // 调用方法
        Method method = stdClass.getMethod("setName", String.class);
        method.invoke(student, "Xiao Hong");
        System.out.println(JSONObject.toJSONString(student));
        // 调用静态方法
        Method parse = stdClass.getMethod("parse", String.class);
        int i = (int) parse.invoke(null, "A");
        System.out.println(i);
        // 调用非public方法
        Method print = stdClass.getDeclaredMethod("print", String.class);
        print.setAccessible(true);
        print.invoke(student, "Xiao Hong");
    }

    private static void polymorphic() throws Exception {
        Method method = Person.class.getMethod("hello");
        method.invoke(new Student());
    }

    private static void constructor() throws Exception {
        Constructor constructor = Student.class.getConstructor(String.class);
        Student student = (Student) constructor.newInstance("Xiao Ming");
        System.out.println(JSONObject.toJSONString(student));
    }
}
