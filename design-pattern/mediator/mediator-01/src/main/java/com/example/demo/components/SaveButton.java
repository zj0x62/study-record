package com.example.demo.components;

import com.example.demo.mediator.Mediator;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * @Author: zhoujing
 * @Date: 2025/2/10 16:53
 * @Description:
 */
public class SaveButton extends JButton implements Component {

    private Mediator mediator;

    public SaveButton() {
        super("Save");
    }

    @Override
    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    protected void fireActionPerformed(ActionEvent actionEvent) {
        mediator.saveChanges();
    }

    @Override
    public String getName() {
        return "SaveButton";
    }
}
