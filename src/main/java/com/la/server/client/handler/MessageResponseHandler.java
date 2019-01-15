package com.la.server.client.handler;


import com.la.server.monitor.gui.MainActivity;
import com.la.server.protocol.response.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {
    private MainActivity mainActivity;

    public MessageResponseHandler(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket messageResponsePacket) {
        String fromUserId = messageResponsePacket.getFromUserId();
        String fromUserName = messageResponsePacket.getFromUserName();
        MainActivity.out.data(messageResponsePacket.getMessage());

        String message = messageResponsePacket.getMessage();
        if (message.isEmpty()) {

        } else {
            String[] splits = message.split("\n|,");
            mainActivity.infoPanel.xAxisPanel.updateGrid(Float.parseFloat(splits[2]));
            mainActivity.infoPanel.yAxisPanel.updateGrid(Float.parseFloat(splits[4]));
            mainActivity.infoPanel.zAxisPanel.updateGrid(Float.parseFloat(splits[6]));
        }
    }
}
