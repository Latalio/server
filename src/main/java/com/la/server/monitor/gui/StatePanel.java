package com.la.server.monitor.gui;

import javax.swing.*;
import java.awt.*;

public class StatePanel extends JPanel {
    private JLabel statePanelTitle;
    private JScrollPane scrollPane;
    public JTextArea statePanelText;

    public StatePanel() {
        this.setLayout(null);
        this.setBounds(0, 330, 800, 170);
        this.setBackground(Color.BLUE);

        this.statePanelTitle = new JLabel("State Info");
        this.statePanelTitle.setBounds(0,0,800,30);

        this.statePanelTitle.setOpaque(true);
        this.statePanelTitle.setBackground(NBSLStyle.COLOR.PANEL_TITLE_BACKGROUND);

        this.statePanelTitle.setFont(NBSLStyle.FONT.PANEL_TITLE);
        this.statePanelTitle.setForeground(NBSLStyle.COLOR.PANEL_TITLE_FOREGROUND);

        this.statePanelTitle.setBorder(BorderFactory.createLineBorder(NBSLStyle.COLOR.PANEL_TITLE_BORDER));
        this.add(this.statePanelTitle);

        //???
        this.statePanelText = new JTextArea();
        //this.statePanelText.setBounds(0, 30, 700, 100);
        this.statePanelText.setBackground(NBSLStyle.COLOR.SENSOR_INFO_BACKGROUND);

        this.statePanelText.setForeground(NBSLStyle.COLOR.TEXT);
        this.statePanelText.setLineWrap(true);
        this.statePanelText.setEditable(true);
        this.scrollPane = new JScrollPane(this.statePanelText);
        this.scrollPane.setBounds(0, 30, 790, 102);
        this.scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        JScrollBar verticalScrollbar = this.scrollPane.getVerticalScrollBar();
        verticalScrollbar.setBackground(NBSLStyle.COLOR.SENSOR_INFO_BACKGROUND);
        this.add(this.scrollPane);


    }
}

