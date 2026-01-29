package com.zyp.demo1.handler.server.out;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

import java.util.Date;

/**
 * @Date:2026/1/29
 * @Author：zyp
 * @Description:
 */
public class ServerOutHandlerC extends ChannelOutboundHandlerAdapter {
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println(new Date() + ": ServerOutHandlerC byteBuf = " + byteBuf);
        super.write(ctx, msg, promise);
    }
}
