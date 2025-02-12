package com.example.demo;

import java.util.Stack;

/**
 * @Author: zhoujing
 * @Date: 2025/2/10 14:05
 * @Description:
 */
public class CommandHistory {

    private Stack<Command> history = new Stack<>();

    public void push(Command c) {
        history.push(c);
    }

    public Command pop() {
        return history.pop();
    }

    public boolean isEmpty() {
        return history.isEmpty();
    }
}
