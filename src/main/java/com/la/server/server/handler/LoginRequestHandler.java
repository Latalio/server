package com.la.server.server.handler;


import com.la.server.protocol.request.LoginRequestPacket;
import com.la.server.protocol.response.LoginResponsePacket;
import com.la.server.server.NettyServer;
import com.la.server.session.Session;
import com.la.server.util.IDUtil;
import com.la.server.util.SessionManager;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) {
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(loginRequestPacket.getVersion());


        // Account and password verification
        if (valid(loginRequestPacket)) {
            String userId = IDUtil.randomId();
            loginResponsePacket.setUserId(userId);
            loginResponsePacket.setUserName(loginRequestPacket.getUserName());
            // loginResponsePacket.setReason(null);
            loginResponsePacket.setSuccess(true);

            NettyServer.out.info("[" + loginRequestPacket.getUserName() + "]登录成功");
            SessionManager.bindSession(new Session(userId, loginRequestPacket.getUserName(), loginRequestPacket.getType()), ctx.channel());

            if (loginRequestPacket.getType().equals("Sensor")) {
                SessionManager.sensorGroup.add(userId);
                NettyServer.out.info("Sensor Group added " + loginRequestPacket.getUserName());

            } else if (loginRequestPacket.getType().equals("Monitor")) {
                SessionManager.monitorGroup.add(userId);
                NettyServer.out.info("Monitor Group added " + loginRequestPacket.getUserName());

            } else {
                NettyServer.out.err("Login Type Error!");
            }

            NettyServer.out.info("Should send an LoginInform");
        } else {
            loginResponsePacket.setReason("账号密码校验失败");
            loginResponsePacket.setSuccess(false);

            NettyServer.out.err("登录失败!");
        }

        // 登录响应
        ctx.channel().writeAndFlush(loginResponsePacket);
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        SessionManager.unbindSession(ctx.channel());
    }
}
