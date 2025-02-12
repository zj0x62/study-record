package com.example.demo;

/**
 * @Author: zhoujing
 * @Date: 2025/2/11 10:13
 * @Description:
 */
public class Main {

    public static void main(String[] args) {
        TextEditor editor = new TextEditor();  // 创建文本编辑器
        History history = new History();       // 创建历史记录管理器

        // 用户输入并保存状态
        editor.write("Hello");        // 文本: Hello
        history.push(editor.save());  // 保存状态1

        editor.write(", World!");      // 文本: Hello, World!
        history.push(editor.save());  // 保存状态2

        editor.write(" How are you?"); // 文本: Hello, World! How are you?
        System.out.println("--- 执行撤销操作 ---");

        // 撤销到上一个状态
        editor.restore(history.pop()); // 恢复状态2 -> Hello, World!
        editor.restore(history.pop()); // 恢复状态1 -> Hello
    }
}
