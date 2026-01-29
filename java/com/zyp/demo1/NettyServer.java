package com.zyp.demo1;

import com.zyp.demo1.handler.FirstServerHandler;
import com.zyp.demo1.handler.server.in.ServerInHandlerA;
import com.zyp.demo1.handler.server.in.ServerInHandlerB;
import com.zyp.demo1.handler.server.in.ServerInHandlerC;
import com.zyp.demo1.handler.server.out.ServerOutHandlerA;
import com.zyp.demo1.handler.server.out.ServerOutHandlerB;
import com.zyp.demo1.handler.server.out.ServerOutHandlerC;
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
                        nioSocketChannel.pipeline().addLast(new ServerInHandlerA());
                        nioSocketChannel.pipeline().addLast(new ServerInHandlerB());
                        nioSocketChannel.pipeline().addLast(new ServerInHandlerC());
                        nioSocketChannel.pipeline().addLast(new FirstServerHandler());


                        nioSocketChannel.pipeline().addLast(new ServerOutHandlerA());
                        nioSocketChannel.pipeline().addLast(new ServerOutHandlerB());
                        nioSocketChannel.pipeline().addLast(new ServerOutHandlerC());

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
