package com.zyp.demo1;

import com.zyp.demo1.common.ConsoleManager;
import com.zyp.demo1.common.LoginConsoleCommand;
import com.zyp.demo1.common.PacketCodeC;
import com.zyp.demo1.common.PacketDecoder;
import com.zyp.demo1.common.PacketEndcoder;
import com.zyp.demo1.handler.client.FirstCilentHandler;
import com.zyp.demo1.handler.client.LoginResponsePacketHandler;
import com.zyp.demo1.handler.client.MessageRsponsePacketHandler;
import com.zyp.demo1.request.LoginRequestPacket;
import com.zyp.demo1.request.MessageReqeustPacket;
import com.zyp.demo1.tools.LoginUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Date:2026/1/27
 * @Author：zyp
 * @Description:
 */
public class NettyClient {
    private static final int MAX_RETRY = 5;

    public static void main(String[] args) {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline()
                                .addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 7, 4))
                                .addLast(new PacketDecoder())
                                .addLast(new LoginResponsePacketHandler())
                                .addLast(new MessageRsponsePacketHandler())
                                .addLast(new PacketEndcoder())
                        ;
                    }
                });
        connect(bootstrap, "127.0.0.1", 8080, MAX_RETRY);
    }

    public static void connect(Bootstrap bootstrap, String host, int port, int retry) {
        AtomicInteger retry0 = new AtomicInteger(retry);
        bootstrap.connect(host, port)
                .addListener(future -> {
                    if (future.isSuccess()) {
                        System.out.println("连接成功");
                    } else if (retry0.get() == 0) {
                        System.out.println("重试完成");
                    } else {
                        int order = (MAX_RETRY - retry0.get()) + 1;
                        System.out.println(new Date() + "连接失败，重试中 第" + order + "次重连....");
                        int delay = 1 << order;
                        int andDecrement = retry0.decrementAndGet();
                        bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, andDecrement), delay, TimeUnit.SECONDS);
                    }
                })
                .addListener(future -> {
                    if (future.isSuccess()) {
                        Channel channel = ((ChannelFuture) future).channel();
                        startConsoleThread(channel);
                    }
                });
    }

    private static void startConsoleThread(Channel channel) {
        ConsoleManager consoleManager = new ConsoleManager();
        Scanner scanner = new Scanner(System.in);
        new Thread(() -> {
            while (!Thread.interrupted()) {
                if (LoginUtil.hasLogin(channel)) {
                    consoleManager.exec(scanner, channel);
                } else {
                    LoginConsoleCommand loginConsoleCommand = new LoginConsoleCommand();
                    loginConsoleCommand.exec(scanner, channel);
                }
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }).start();
    }
}
