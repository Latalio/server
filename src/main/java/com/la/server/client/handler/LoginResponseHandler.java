package com.la.server.client.handler;


import com.la.server.client.NettyClient;
import com.la.server.gui.MainActivity;
import com.la.server.protocol.response.LoginResponsePacket;
import com.la.server.session.Session;
import com.la.server.util.SessionManager;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket loginResponsePacket) {
        String userId = loginResponsePacket.getUserId();
        String userName = loginResponsePacket.getUserName();

        if (loginResponsePacket.isSuccess()) {
            NettyClient.out.info("Login succeed. [User ID]: " + userId);
            MainActivity.out.info("Login succeed. [User ID]: " + userId);
            SessionManager.bindSession(new Session(userId, userName, "Monitor"), ctx.channel());

            String[] index = {"Sensor Info", "-"};
            Object[][] data = {
                    {"Device", userName},
                    {"Status", "online"},
                    {"UUID", userId},
                    {"Type", "Sensor"},
                    {"Login Time", new SimpleDateFormat("HH:mm:ss").format(new Date())}
            };
            DefaultTableModel defaultTableModel = (DefaultTableModel)MainActivity.consolePanel.infoTable.getModel();
            defaultTableModel.setDataVector(data, index);
            defaultTableModel.fireTableStructureChanged();
        } else {
            NettyClient.out.info("Login failed. [Reason]: " + loginResponsePacket.getReason());
            MainActivity.out.info("Login failed. [Reason]: " + loginResponsePacket.getReason());
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        NettyClient.out.info("客户端连接被关闭!");
    }
}
