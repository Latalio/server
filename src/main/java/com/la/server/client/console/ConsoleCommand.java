package com.la.server.client.console;

import io.netty.channel.Channel;

public interface ConsoleCommand {
    void exec(Channel channel, String message);
}
