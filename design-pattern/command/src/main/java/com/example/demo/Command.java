package com.example.demo;

/**
 * @Author: zhoujing
 * @Date: 2025/2/10 14:00
 * @Description:
 */
public abstract class Command {

    public Editor editor;
    private String backup;

    Command(Editor editor) {
        this.editor = editor;
    }

    void backup() {
        backup = editor.textField.getText();
    }

    public void undo() {
        editor.textField.setText(backup);
    }

    public abstract boolean execute();
}
