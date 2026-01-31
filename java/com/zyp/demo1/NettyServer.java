package com.zyp.demo1;

import com.zyp.demo1.common.PacketDecoder;
import com.zyp.demo1.common.PacketEndcoder;
import com.zyp.demo1.handler.server.CreateGroupRequestHandler;
import com.zyp.demo1.handler.server.LoginRequestPacketHandler;
import com.zyp.demo1.handler.server.MessageReqeustPacketHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * @Date:2026/1/26
 * @Author：zyp
 * @Description:
 */
public class NettyServer {

    public static void main(String[] args) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup,workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        nioSocketChannel.pipeline().addLast(new PacketDecoder());
                        nioSocketChannel.pipeline().addLast(new LoginRequestPacketHandler());
                        nioSocketChannel.pipeline().addLast(new MessageReqeustPacketHandler());
                        nioSocketChannel.pipeline().addLast(new CreateGroupRequestHandler());
                        nioSocketChannel.pipeline().addLast(new PacketEndcoder());

                    }
                });
        bind(serverBootstrap,8080);
    }

    public static void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if (future.isSuccess()) {
                    System.out.println("端口【" + port + "】绑定成功");
                } else {
                    System.out.println("端口【" + port + "】绑定失败");
                    bind(serverBootstrap, port + 1);
                }
            }
        });
        serverBootstrap.handler(new ChannelInitializer<NioServerSocketChannel>() {
            @Override
            protected void initChannel(NioServerSocketChannel nioServerSocketChannel) throws Exception {
                System.out.println("服务端启动中");
            }
        });
        serverBootstrap.attr(AttributeKey.newInstance("serverName"),"nettyServer");
    }
}
