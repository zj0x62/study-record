package com.example.demo.components;

import com.example.demo.mediator.Mediator;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * @Author: zhoujing
 * @Date: 2025/2/10 16:52
 * @Description:
 */
public class DeleteButton extends JButton implements Component {

    private Mediator mediator;

    public DeleteButton() {
        super("Del");
    }

    @Override
    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    protected void fireActionPerformed(ActionEvent actionEvent) {
        mediator.deleteNote();
    }

    @Override
    public String getName() {
        return "DelButton";
    }
}
