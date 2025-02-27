package com.example.demo.mediator;

import com.example.demo.components.Component;

import javax.swing.*;

/**
 * @Author: zhoujing
 * @Date: 2025/2/10 16:55
 * @Description: 通用的中介者接口
 */
public interface Mediator {

    void addNewNote(Note note);
    void deleteNote();
    void getInfoFromList(Note note);
    void saveChanges();
    void markNote();
    void clear();
    void sendToFilter(ListModel listModel);
    void setElementsList(ListModel list);
    void registerComponent(Component component);
    void hideElements(boolean flag);
    void createGUI();
}
