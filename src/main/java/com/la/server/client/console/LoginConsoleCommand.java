package com.la.server.client.console;



import com.la.server.client.NettyClient;
import com.la.server.protocol.request.LoginRequestPacket;
import io.netty.channel.Channel;


public class LoginConsoleCommand {

    public void exec(Channel channel) {
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();

        loginRequestPacket.setUserName("Admin");
        loginRequestPacket.setPassword("password");
        loginRequestPacket.setType("Monitor");
        NettyClient.out.info(
                "Login info: [Username]" + loginRequestPacket.getUserName() +
                " [Type]" + loginRequestPacket.getType()
        );

        // 发送登录数据包
        channel.writeAndFlush(loginRequestPacket);
        waitForLoginResponse();
    }

    private static void waitForLoginResponse() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ignored) {
        }
    }
}
