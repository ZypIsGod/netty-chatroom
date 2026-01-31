package com.zyp.demo1.handler.client;

import com.zyp.demo1.common.PacketCodeC;
import com.zyp.demo1.common.PacketCodeC2;
import com.zyp.demo1.request.LoginRequestPacket;
import com.zyp.demo1.response.LoginResponsePacket;
import com.zyp.demo1.tools.LoginUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;
import java.util.UUID;

/**
 * @Date:2026/1/29
 * @Author：zyp
 * @Description:
 */
public class LoginResponsePacketHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket msg) throws Exception {
        if (msg.isSuccess()) {
            LoginUtil.markAsLogin(ctx.channel());
            System.out.println(new Date() + ":客户端登录成功");
        } else {
            System.out.println(new Date() + ":客户端登录失败，原因：" + msg.getReason());
        }
    }

/*    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date() + ": 客户端登录中....");
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUsername("flash");
        loginRequestPacket.setPassword("pwd");
        ByteBufAllocator alloc = ctx.alloc();
        ByteBuf encode = PacketCodeC2.INSTANCE.encode(alloc, loginRequestPacket);
        ctx.channel().writeAndFlush(encode);
    }*/
}
