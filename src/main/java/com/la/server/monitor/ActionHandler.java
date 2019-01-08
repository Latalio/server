package com.la.server.monitor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ActionHandler implements ActionListener {

    private JTextArea textArea;

    public ActionHandler(JTextArea textArea) {
        this.textArea = textArea;
    }


    public void actionPerformed(ActionEvent e) {
        textArea.setText("shabi swing");
    }
}