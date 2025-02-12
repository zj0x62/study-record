package com.example.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhoujing
 * @Date: 2025/2/11 10:06
 * @Description: 管理者：保存备忘录的历史记录
 */
public class History {

    private final List<TextMemento> mementos = new ArrayList<>();

    /**
     * 保存一个备忘录
     */
    public void push(TextMemento memento) {
        mementos.add(memento);
    }

    /**
     * 撤销操作：取出最近的备忘录
     */
    public TextMemento pop() {
        if (mementos.isEmpty()) {
            return null;
        }

        int lastIndex = mementos.size() - 1;
        TextMemento lastMemento = mementos.get(lastIndex);
        mementos.remove(lastIndex);
        return lastMemento;
    }
}
