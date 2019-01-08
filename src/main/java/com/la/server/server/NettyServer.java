package com.la.server.server;

import com.la.server.codec.PacketDecoder;
import com.la.server.codec.PacketEncoder;
import com.la.server.codec.Splitter;
import com.la.server.handler.IMIdleStateHandler;
import com.la.server.server.handler.AuthHandler;
import com.la.server.server.handler.HeartBeatRequestHandler;
import com.la.server.server.handler.LoginRequestHandler;
import com.la.server.server.handler.MessageRequestHandler;

import java.util.Date;

import com.la.server.util.LogString;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyServer {

    private static final int PORT = 23333;

    private static LogString logString = new LogString();

    public static void main(String[] args) {
        NioEventLoopGroup boosGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        final ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(boosGroup, workerGroup);
        serverBootstrap.channel(NioServerSocketChannel.class);
        serverBootstrap.option(ChannelOption.SO_BACKLOG, 1024);
        serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
        serverBootstrap.childOption(ChannelOption.TCP_NODELAY, true);
        serverBootstrap.childHandler(new ChannelInitializer<NioSocketChannel>() {
            protected void initChannel(NioSocketChannel ch) {
                ch.pipeline().addLast(new IMIdleStateHandler());
                ch.pipeline().addLast(new Splitter());
                ch.pipeline().addLast(new PacketDecoder());
                ch.pipeline().addLast(new LoginRequestHandler());
                ch.pipeline().addLast(new HeartBeatRequestHandler());
                ch.pipeline().addLast(new AuthHandler());
                ch.pipeline().addLast(new MessageRequestHandler());
                ch.pipeline().addLast(new PacketEncoder());
            }
        });


        bind(serverBootstrap, PORT);
    }

    private static void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port).addListener(future -> {
            if (future.isSuccess()) {
                out.info(": 端口[" + port + "]绑定成功!");
            } else {
                out.err("端口[" + port + "]绑定失败!");
            }
        });
    }

    public static final class out {
        public static void info(String string) {
            System.out.println(logString.info(string));
        }

        public static void err(String string) {
            System.out.println(logString.err(string));
        }
    }



}
