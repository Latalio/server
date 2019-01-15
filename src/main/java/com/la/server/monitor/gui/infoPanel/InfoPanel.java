package com.la.server.monitor.gui.infoPanel;


import com.la.server.monitor.gui.NBSLStyle;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class InfoPanel extends JPanel {
    private JLabel infoPanelTitle;
    public JTextArea infoTextArea;
    public AxisPanel xAxisPanel;
    public AxisPanel yAxisPanel;
    public AxisPanel zAxisPanel;

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

        this.xAxisPanel = new AxisPanel();
        this.xAxisPanel.setBounds(360, 30, 280, 100);
        //this.xAxisPanel.setBorder(BorderFactory.createTitledBorder("x-Axis"));
        //this.xAxisPanel.setBackground(NBSLStyle.COLOR.SENSOR_INFO_BACKGROUND);
        this.add(this.xAxisPanel);

        this.yAxisPanel = new AxisPanel();
        this.yAxisPanel.setBounds(360, 130, 280, 100);
        this.yAxisPanel.setBorder(BorderFactory.createRaisedBevelBorder());
        //this.yAxisPanel.setBackground(NBSLStyle.COLOR.SENSOR_DATA_BACKGROUND);
        this.add(this.yAxisPanel);

        this.zAxisPanel = new AxisPanel();
        this.zAxisPanel.setBounds(360, 230, 280, 100);
        this.zAxisPanel.setForeground(Color.BLUE);
        //this.zAxisPanel.setBackground(NBSLStyle.COLOR.SENSOR_INFO_BACKGROUND);
        this.add(this.zAxisPanel);

    }

    public class AxisPanel extends JPanel{
        private int bias = 0;
        private Vector<Integer> values = new Vector<Integer>();

        public void paintComponent(Graphics g){
            super.paintComponent(g);
            setBackground(NBSLStyle.COLOR.SENSOR_GRAPH_BACKGROUND);
            drawGrid(g, bias);

        }

        public void drawGrid(Graphics g, int bias) {
            int i;
            int[] xarray = {0,45,90,135,180,225,270};
            int[] yarray = {0,10,20,30,40,50,60,70,80,90,100};

            Polygon p = new Polygon();
            p.addPoint(280-bias,100);
            for (i=0;i<values.size();i++) {
                p.addPoint(280-bias+9*i,values.get(i) + 50);
            }
            p.addPoint(280, 50);
            p.addPoint(280-bias,50);
            g.setColor(new Color(62,134,160));
            g.fillPolygon(p);


            for(i=0; i<xarray.length; i++) {
                g.drawLine(xarray[i]-(bias%45),0,xarray[i]-(bias%45),100);
            }
            for(i=0; i<yarray.length; i++) {
                g.drawLine(0,yarray[i],280,yarray[i]);
            }

        }

        public void updateGrid(float value) {
            bias = bias + 9;
            int tmp = (int) (value/10 * 50);
            values.add(tmp);
            repaint();
        }
    }



}
