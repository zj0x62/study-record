package com.example.demo;

import org.springframework.context.ApplicationEvent;

/**
 * @Author: zhoujing
 * @Date: 2024/7/19 10:16
 * @Description:
 */
public class MyEvent extends ApplicationEvent {

    private Task task;

    public MyEvent(Task task) {
        super(task);
        this.task = task;
    }

    public Task getTask() {
        return task;
    }
}
