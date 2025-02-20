package com.example.demo;

/**
 * @Author: zhoujing
 * @Date: 2025/2/13 14:50
 * @Description:
 */
public class Student extends Person {

    public int score;

    private int grade;

    public Student() {

    }

    public Student(String name) {
        super(name);
    }

    public static int parse(String type) {
        return 99;
    }

    public int getScore(String type) {
        return 99;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getGrade(int year) {
        return 1;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    private void print(String type) {
        System.out.println("print: " + type);
    }

    @Override
    public void hello() {
        System.out.println("Student:hello");
    }
}
