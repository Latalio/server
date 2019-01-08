package com.la.server.server.handler;


import com.la.server.protocol.request.HeartBeatRequestPacket;
import com.la.server.protocol.response.HeartBeatResponsePacket;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class HeartBeatRequestHandler extends SimpleChannelInboundHandler<HeartBeatRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HeartBeatRequestPacket requestPacket) {
        ctx.channel().writeAndFlush(new HeartBeatResponsePacket());
    }
}
