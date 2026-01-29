package com.zyp.demo1.handler.server.in;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

/**
 * @Date:2026/1/29
 * @Author：zyp
 * @Description:
 */
public class ServerInHandlerA extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf)msg;
        System.out.println(new Date()+": ServerInHandlerA byteBuf = "+byteBuf);
        super.channelRead(ctx, msg);
    }
}
