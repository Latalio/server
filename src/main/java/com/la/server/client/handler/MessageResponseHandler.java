package com.la.server.client.handler;


import com.la.server.client.NettyClient;
import com.la.server.gui.MainActivity;
import com.la.server.protocol.response.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket messageResponsePacket) {
        String fromUserId = messageResponsePacket.getFromUserId();
        String fromUserName = messageResponsePacket.getFromUserName();
        NettyClient.out.info(fromUserId + ":" + fromUserName + " -> " + messageResponsePacket
                .getMessage());
        MainActivity.out.info(fromUserId + ":" + fromUserName + " -> " + messageResponsePacket
                .getMessage());
    }
}
