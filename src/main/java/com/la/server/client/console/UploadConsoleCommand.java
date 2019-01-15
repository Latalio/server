package com.la.server.client.console;


import com.la.server.protocol.request.MessageRequestPacket;
import io.netty.channel.Channel;

public class UploadConsoleCommand {

    public void exec(Channel channel, String message) {
        channel.writeAndFlush(new MessageRequestPacket(message));
    }
}

