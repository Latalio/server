package com.la.server.gui;

import com.la.server.util.LogString;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConsolePanel extends JPanel {
    private JLabel infoTitle;
    public JTable infoTable;
    private CommandPanel commandPanel;
    public ButtonTag buttonTag = new ButtonTag();
    private static final Byte MASK = 1;


    public ConsolePanel() {
        this.setLayout(null);
        this.setBounds(0, 0, 160, 330);
        //this.setBackground(NBSLStyle.COLOR.SENSOR_DATA_BACKGROUND);

        String[] index = {"Sensor Info", "-"};
        Object[][] data = {
                {"Device", "-"},
                {"Status", "-"},
                {"UUID", "-"},
                {"登录时间", "-"},
                {"在线时长", "-"}
        };


        this.infoTitle = new JLabel("Sensor Info");
        this.infoTitle.setBounds(0, 0, 160, 30);
        this.infoTitle.setOpaque(true);  //设置背景不透明，这样才能看到背景色效果；
        this.infoTitle.setBackground(NBSLStyle.COLOR.PANEL_TITLE_BACKGROUND);

        this.infoTitle.setFont(NBSLStyle.FONT.PANEL_TITLE);
        this.infoTitle.setForeground(NBSLStyle.COLOR.PANEL_TITLE_FOREGROUND);

        this.infoTitle.setBorder(BorderFactory.createLineBorder(NBSLStyle.COLOR.PANEL_TITLE_BORDER));
        this.add(this.infoTitle);


        DefaultTableModel defaultTableModel = new DefaultTableModel(data, index);
        this.infoTable = new JTable(defaultTableModel);
        this.infoTable.setBounds(0, 30, 160, 150);
        this.infoTable.setRowHeight(30);
        this.infoTable.setBackground(NBSLStyle.COLOR.SENSOR_INFO_BACKGROUND);
        this.infoTable.setFont(NBSLStyle.FONT.TEXT);
        this.infoTable.setForeground(NBSLStyle.COLOR.TEXT);
        this.add(this.infoTable);

        this.commandPanel = new CommandPanel();
        this.commandPanel.setBounds(0, 180, 250, 200);
        this.add(this.commandPanel);

        //this.add(this.commandPanel, JScrollPane.UPPER_LEFT_CORNER);
    }

    public class CommandPanel extends JPanel {
        private JLabel commandTitle;
        private JLabel dataSend;
        private JLabel controlSend;
        private JButton dataSendButton;
        private JButton controlSendButton;

        public CommandPanel() {
            this.setLayout(null);
            this.setBackground(NBSLStyle.COLOR.SENSOR_INFO_BACKGROUND);
            this.commandTitle = new JLabel("Console");
            this.commandTitle.setBounds(0, 0, 160, 30);

            this.commandTitle.setOpaque(true);
            this.commandTitle.setBackground(NBSLStyle.COLOR.PANEL_TITLE_BACKGROUND);

            this.commandTitle.setFont(NBSLStyle.FONT.PANEL_TITLE);
            this.commandTitle.setForeground(NBSLStyle.COLOR.PANEL_TITLE_FOREGROUND);

            this.commandTitle.setBorder(BorderFactory.createLineBorder(NBSLStyle.COLOR.PANEL_TITLE_BORDER));
            this.add(this.commandTitle);


            this.dataSend = new JLabel("Data Transmission: Off");
            this.dataSend.setBounds(0, 30, 160, 30);

            this.dataSend.setFont(NBSLStyle.FONT.TEXT);
            this.dataSend.setForeground(NBSLStyle.COLOR.TEXT);
            this.add(this.dataSend);

            this.dataSendButton =  new JButton("Start");
            this.dataSendButton.setBounds(40, 65, 80, 20);

            this.dataSendButton.setBackground(NBSLStyle.COLOR.BUTTON_BACKGROUND);
            this.add(this.dataSendButton);

            this.dataSendButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MainActivity.statePanel.statePanelText.append("Touched data send button");

                    if (buttonTag.dataSend == 0) {
                        buttonTag.dataSend = 1;
                        dataSendButton.setText("End");
                    } else if (buttonTag.dataSend == 1) {
                        buttonTag.dataSend = 0;
                        dataSendButton.setText("Start");
                    } else {
                        System.out.println(LogString.err("button tag setting error"));
                    }


                }
            });

            this.controlSend = new JLabel("Remote Control: Off");
            this.controlSend.setBounds(0, 90, 160, 30);

            this.controlSend.setFont(NBSLStyle.FONT.TEXT);
            this.controlSend.setForeground(NBSLStyle.COLOR.TEXT);

            this.add(this.controlSend);

            this.controlSendButton = new JButton("Open");
            this.controlSendButton.setBounds(40, 125, 80, 20);

            this.controlSendButton.setBackground(NBSLStyle.COLOR.BUTTON_BACKGROUND);
            this.add(this.controlSendButton);
        }
    }

    public class ButtonTag {
        public Byte dataSend = 0;
        public Byte controlSend = 0;
    }

}