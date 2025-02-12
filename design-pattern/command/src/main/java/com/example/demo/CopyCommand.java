package com.example.demo;

/**
 * @Author: zhoujing
 * @Date: 2025/2/10 14:01
 * @Description:
 */
public class CopyCommand extends Command {

    public CopyCommand(Editor editor) {
        super(editor);
    }

    @Override
    public boolean execute() {
        editor.clipboard = editor.textField.getSelectedText();
        return false;
    }
}
