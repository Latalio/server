package com.la.server.gui.infoPanel;


import com.la.server.gui.NBSLStyle;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel {
    private JLabel infoPanelTitle;
    private JTextArea infoTextArea;
    private JPanel xAxisPanel;
    private JPanel yAxisPanel;
    private JPanel zAxisPanel;

    public InfoPanel() {
        InfoFormat infoFormat = new InfoFormat();
        this.setLayout(null);
        this.setBounds(160, 0, 640, 330);
        this.setBackground(Color.GREEN);

        this.infoPanelTitle = new JLabel("Sensor Data");
        this.infoPanelTitle.setBounds(0, 0, 640, 30);

        this.infoPanelTitle.setOpaque(true);
        this.infoPanelTitle.setBackground(NBSLStyle.COLOR.PANEL_TITLE_BACKGROUND);

        this.infoPanelTitle.setFont(NBSLStyle.FONT.PANEL_TITLE);
        this.infoPanelTitle.setForeground(NBSLStyle.COLOR.PANEL_TITLE_FOREGROUND);

        this.infoPanelTitle.setBorder(BorderFactory.createLineBorder(NBSLStyle.COLOR.PANEL_TITLE_BORDER));

        this.add(this.infoPanelTitle);

        this.infoTextArea = new JTextArea(infoFormat.out());
        //this.infoTextArea.setFont(new Font("黑体", Font.PLAIN, 50));
        this.infoTextArea.setBounds(0, 30, 360, 300);
        this.infoTextArea.setBackground(NBSLStyle.COLOR.SENSOR_DATA_BACKGROUND);

        this.infoTextArea.setForeground(NBSLStyle.COLOR.TEXT);
        this.add(this.infoTextArea);

        this.xAxisPanel = new JPanel();
        this.xAxisPanel.setBounds(360, 30, 280, 100);
        this.xAxisPanel.setBackground(Color.BLUE);
        this.add(this.xAxisPanel);

        this.yAxisPanel = new JPanel();
        this.yAxisPanel.setBounds(360, 130, 280, 100);
        this.yAxisPanel.setBackground(Color.CYAN);
        this.add(this.yAxisPanel);

        this.zAxisPanel = new JPanel();
        this.zAxisPanel.setBounds(360, 230, 280, 100);
        this.zAxisPanel.setBackground(Color.GREEN);
        this.add(this.zAxisPanel);

    }
}
