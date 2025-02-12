package com.example.demo;

/**
 * @Author: zhoujing
 * @Date: 2025/2/11 10:02
 * @Description: 备忘录（Memento）：存储文本编辑器的某个时刻的状态
 */
public class TextMemento {

    private final String text; // 保存的文本内容

    /**
     * 构造方法（仅允许同包的Originator访问）
     */
    TextMemento(String text) {
        this.text = text;
    }

    /**
     *  获取保存的文本（仅允许Originator访问）
     */
    String getSavedText() {
        return text;
    }
}
