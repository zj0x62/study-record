package com.example.demo.components;

import com.example.demo.mediator.Mediator;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * @Author: zhoujing
 * @Date: 2025/2/10 16:54
 * @Description:
 */
public class Title extends JTextField implements Component {

    private Mediator mediator;

    @Override
    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    protected void processComponentKeyEvent(KeyEvent keyEvent) {
        mediator.markNote();
    }

    @Override
    public String getName() {
        return "Title";
    }
}
