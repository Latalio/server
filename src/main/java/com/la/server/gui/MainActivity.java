package com.la.server.gui;


import com.la.server.client.NettyClient;
import com.la.server.gui.infoPanel.InfoPanel;
import com.la.server.util.LogString;

import javax.swing.*;
import java.awt.*;

public class MainActivity {

    public static ConsolePanel consolePanel;
    public static JPanel infoPanel;
    public static StatePanel statePanel;

    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 500;

    private MainActivity() {
        JFrame frame = new JFrame("MainActivity");
        frame.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        frame.setResizable(false);

        consolePanel = new ConsolePanel();
        statePanel = new StatePanel();
        infoPanel = new InfoPanel();

        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        mainPanel.setLayout(null);
        mainPanel.add(consolePanel);
        mainPanel.add(infoPanel);
        mainPanel.add(statePanel);

        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static class out {
        public static void info(String string) {
            statePanel.statePanelText.append(LogString.info(string));
        }

        public static void err(String string) {
            statePanel.statePanelText.append(LogString.err(string));
        }

        public static void data() {

        }
    }
    public static void main(String[] args) {
        MainActivity mainActivity = new MainActivity();

        SwingWorker swingWorker = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                NettyClient nettyClient = new NettyClient(consolePanel.buttonTag);
                return null;
            }
        };
        swingWorker.execute();


    }

}
