package com.example.demo.components;

import com.example.demo.mediator.Mediator;
import com.example.demo.mediator.Note;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * @Author: zhoujing
 * @Date: 2025/2/10 16:51
 * @Description:
 */
public class AddButton extends JButton implements Component {

    private Mediator mediator;

    public AddButton() {
        super("Add");
    }

    @Override
    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    protected void fireActionPerformed(ActionEvent actionEvent) {
        mediator.addNewNote(new Note());
    }

    @Override
    public String getName() {
        return "AddButton";
    }
}
