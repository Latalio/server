package com.la.server.client;


import com.la.server.client.console.LoginConsoleCommand;
import com.la.server.client.console.UploadConsoleCommand;
import com.la.server.client.handler.HeartBeatTimerHandler;
import com.la.server.client.handler.LoginResponseHandler;
import com.la.server.client.handler.MessageResponseHandler;
import com.la.server.codec.PacketDecoder;
import com.la.server.codec.PacketEncoder;
import com.la.server.codec.Splitter;
import com.la.server.gui.ConsolePanel;
import com.la.server.gui.MainActivity;
import com.la.server.handler.IMIdleStateHandler;
import com.la.server.util.LogString;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author 闪电侠
 */
public class NettyClient {
    private static final int MAX_RETRY = 5;
    private static final String HOST = "222.20.25.214";
    private static final int PORT = 23333;
    private static LogString logString = new LogString();

    public ConsolePanel.ButtonTag buttonTag;


    public NettyClient(ConsolePanel.ButtonTag buttonTag) {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap
                .group(workerGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) {
                        ch.pipeline().addLast(new IMIdleStateHandler());
                        ch.pipeline().addLast(new Splitter());
                        ch.pipeline().addLast(new PacketDecoder());
                        ch.pipeline().addLast(new LoginResponseHandler());
                        ch.pipeline().addLast(new MessageResponseHandler());
                        ch.pipeline().addLast(new PacketEncoder());
                        ch.pipeline().addLast(new HeartBeatTimerHandler());

                    }
                });

        connect(bootstrap, HOST, PORT, MAX_RETRY);

        this.buttonTag = buttonTag;
    }

    private void connect(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println(LogString.info(String.format("Connection succeeded. [host]: %s [port]: %s", HOST,PORT)));
                MainActivity.out.info(String.format("Connection succeeded. [host]: %s [port]: %s", HOST,PORT));

                Channel channel = ((ChannelFuture) future).channel();
                start(channel);
            } else if (retry == 0) {
                System.err.println("重试次数已用完，放弃连接！");
            } else {
                // 第几次重连
                int order = (MAX_RETRY - retry) + 1;
                // 本次重连的间隔
                int delay = 1 << order;
                System.err.println(new Date() + ": 连接失败，第" + order + "次重连……");
                bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit
                        .SECONDS);
            }
        });
    }

    private void start(Channel channel) {
        LoginConsoleCommand loginConsoleCommand = new LoginConsoleCommand();
        loginConsoleCommand.exec(channel);

        new Thread(new Runnable() {
            @Override
            public void run() {
                int lastDataSend = 0;
                while(true) {
                   try {
                       if ((lastDataSend == 0) && (buttonTag.dataSend == 1)) {
                           lastDataSend = 1;
                           new UploadConsoleCommand().exec(channel, "Start");
                       } else if ((lastDataSend == 1) && (buttonTag.dataSend == 0)) {
                           lastDataSend = 0;
                           new UploadConsoleCommand().exec(channel, "End");
                       }
                       Thread.sleep(500);
                   } catch (Exception e){
                       e.printStackTrace();
                   }
                }
            }
        }).start();


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
