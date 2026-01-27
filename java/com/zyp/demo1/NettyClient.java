package com.zyp.demo1;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;
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

                    }
                });
        connect(bootstrap, "1", 80, MAX_RETRY);
    }

    public static void connect(Bootstrap bootstrap, String host, int port, int retry) {
        AtomicInteger retry0 = new AtomicInteger(retry);
        bootstrap.connect(host, port).addListener(future -> {
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
        });
    }
}
