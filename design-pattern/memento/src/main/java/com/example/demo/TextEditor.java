package com.example.demo;

/**
 * @Author: zhoujing
 * @Date: 2025/2/11 10:04
 * @Description: 发起人（Originator）：文本编辑器，负责创建备忘录和恢复状态
 */
public class TextEditor {

    private String text = ""; // 当前文本内容

    /**
     * 输入文本
     */
    public void write(String text) {
        this.text += text;
        System.out.println("当前文本：" + this.text);
    }

    /**
     * 创建备忘录（保存当前状态）
     */
    public TextMemento save() {
        System.out.println("保存状态：" + text);
        return new TextMemento(text);
    }

    /**
     * 从备忘录恢复状态
     */
    public void restore(TextMemento textMemento) {
        this.text = textMemento.getSavedText();
        System.out.println("恢复状态：" + text);
    }
}
