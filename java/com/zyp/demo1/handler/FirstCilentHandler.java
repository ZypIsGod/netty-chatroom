package com.zyp.demo1.handler;

import com.zyp.demo1.LoginRequestPacket;
import com.zyp.demo1.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

/**
 * @Date:2026/1/27
 * @Author：zyp
 * @Description:
 */
public class FirstCilentHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(new Date()+": 客户端登录中....");
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUsername("flash");
        loginRequestPacket.setPassword("pwd");
        ByteBufAllocator alloc = ctx.alloc();
        ByteBuf encode = PacketCodeC.INSTANCE.encode(alloc, loginRequestPacket);
        ctx.channel().writeAndFlush(encode);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date() + "客户端写出数据...");
        ByteBuf buffer = getByteBuf(ctx);
        ctx.channel().writeAndFlush(buffer);
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        ByteBuf buffer = ctx.alloc().buffer();
        byte[] bytes = "你好哇".getBytes(Charset.forName("utf-8"));
        buffer.writeBytes(bytes);
        return buffer;
    }
}
