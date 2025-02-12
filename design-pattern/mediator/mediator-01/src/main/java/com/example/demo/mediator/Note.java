package com.example.demo.mediator;

/**
 * @Author: zhoujing
 * @Date: 2025/2/10 16:55
 * @Description: 笔记类
 */
public class Note {

    private String name;
    private String text;

    public Note() {
        name = "New note";
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return name;
    }
}
