package com.la.server.server.handler;

import com.la.server.protocol.request.MessageRequestPacket;
import com.la.server.protocol.response.MessageResponsePacket;
import com.la.server.server.NettyServer;
import com.la.server.session.Session;
import com.la.server.util.SessionManager;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.ArrayList;
import java.util.List;

@ChannelHandler.Sharable
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageRequestPacket) {
        long begin = System.currentTimeMillis();
        List<Channel> channelGroup = new ArrayList<>();


        // 1.拿到消息发送方的会话信息
        Session session = SessionManager.getSession(ctx.channel());

        // 2.通过消息发送方的会话信息构造要发送的消息
        String senderId = session.getUserId();
        String senderName = session.getUserName();
        String senderType = session.getUserType();
        String message = messageRequestPacket.getMessage();

        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setFromUserId(senderId);
        messageResponsePacket.setFromUserName(senderName);
        messageResponsePacket.setMessage(message);
        NettyServer.out.info(
                "[From]: " + senderType + "," + senderName +
                        "[Message]: " + message);


        // 3.拿到消息接收方的 channel
        if (senderType.equals("Sensor")) {
            for (String userId : SessionManager.monitorGroup) {
                channelGroup.add(SessionManager.getChannel(userId));
            }
            //NettyServer.out.info(String.format("Sensor group length: %d", channelGroup.size()));

        } else if (senderType.equals("Monitor")) {
            for (String userId : SessionManager.sensorGroup) {
                channelGroup.add(SessionManager.getChannel(userId));
            }
            //NettyServer.out.info(String.format("Monitor group length: %d", channelGroup.size()));
        } else {
            NettyServer.out.err("MessageRequestHandler error! session. usertype is " +
                    senderType);
        }

        // 4.将消息发送给消息接收方
        for (Channel channel : channelGroup) {
            if (channel != null && SessionManager.hasLogin(channel)) {
                channel.writeAndFlush(messageResponsePacket).addListener(future -> {
                    if (future.isDone()) {
                    }
                });
            } else {
                NettyServer.out.err("No client online.");
            }
        }


    }
}
