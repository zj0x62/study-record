package com.example.demo.visitor;

import com.example.demo.user.impl.Student;
import com.example.demo.user.impl.Teacher;

/**
 * @Author: zhoujing
 * @Date: 2025/2/11 17:24
 * @Description:
 */
public interface Visitor {

    // 访问学生信息
    void visit(Student student);

    // 访问老师信息
    void visit(Teacher teacher);
}
