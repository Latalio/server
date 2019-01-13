package com.la.server.gui.infoPanel;


import com.la.server.gui.NBSLStyle;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import java.util.Vector;

public class InfoPanel extends JPanel {
    private JLabel infoPanelTitle;
    public JTextArea infoTextArea;
    private AxisPanel xAxisPanel;
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

        this.xAxisPanel = new AxisPanel();
        this.xAxisPanel.setBounds(360, 30, 280, 100);
        //this.xAxisPanel.setBackground(Color.BLUE);
        this.xAxisPanel.updateGrid();
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

    private class AxisPanel extends JPanel{
        private int bias;
        private Vector<Integer> values = new Vector<Integer>();

        public void paintComponent(Graphics g){
            super.paintComponent(g);
            setBackground(Color.yellow); //背景色为黄色
            drawGrid(g, bias);

        }

        public void drawGrid(Graphics g, int bias) {
            int i;
            int[] xarray = {0,45,90,135,180,225,270};
            int[] yarray = {0,10,20,30,40,50,60,70,80,90,100};

            Polygon p = new Polygon();
            p.addPoint(280-bias,100);
            for (i=0;i<values.size();i++) {
                p.addPoint(280-bias+9*i,values.get(i));
            }
            p.addPoint(280, 100);
            p.addPoint(280-bias,100);
            g.setColor(Color.BLUE);
            g.fillPolygon(p);




            for(i=0; i<xarray.length; i++) {
                g.drawLine(xarray[i]-(bias%45),0,xarray[i]-(bias%45),100);
            }
            for(i=0; i<yarray.length; i++) {
                g.drawLine(0,yarray[i],280,yarray[i]);
            }

        }

        public void updateGrid() {
            Graphics g = this.getGraphics();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int i;
                    for(i=0;i<100;i++) {
                        try {
                            bias = i*9;
                            values.add((i*100)%200);
                            repaint();
                            Thread.sleep(1000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }
            }).start();
        }
    }

}
