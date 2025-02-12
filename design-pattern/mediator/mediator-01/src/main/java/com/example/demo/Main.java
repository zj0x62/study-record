package com.example.demo;

import com.example.demo.components.AddButton;
import com.example.demo.components.DeleteButton;
import com.example.demo.components.Filter;
import com.example.demo.components.List;
import com.example.demo.components.SaveButton;
import com.example.demo.components.TextBox;
import com.example.demo.components.Title;
import com.example.demo.mediator.Editor;
import com.example.demo.mediator.Mediator;

import javax.swing.*;

/**
 * @Author: zhoujing
 * @Date: 2025/2/10 16:58
 * @Description:
 */
public class Main {

    public static void main(String[] args) {
        Mediator mediator = new Editor();

        mediator.registerComponent(new Title());
        mediator.registerComponent(new TextBox());
        mediator.registerComponent(new AddButton());
        mediator.registerComponent(new DeleteButton());
        mediator.registerComponent(new SaveButton());
        mediator.registerComponent(new List(new DefaultListModel()));
        mediator.registerComponent(new Filter());

        mediator.createGUI();
    }
}
