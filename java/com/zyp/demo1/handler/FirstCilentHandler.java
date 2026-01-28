package com.zyp.demo1.handler;

import com.zyp.demo1.common.Packet;
import com.zyp.demo1.request.LoginRequestPacket;
import com.zyp.demo1.common.PacketCodeC;
import com.zyp.demo1.response.LoginResponsePacket;
import com.zyp.demo1.response.MessageRsponsePacket;
import com.zyp.demo1.tools.LoginUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
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
        ByteBuf byteBuf = (ByteBuf) msg;
        Packet decodePacket = PacketCodeC.INSTANCE.decode(byteBuf);
        if (decodePacket instanceof LoginResponsePacket) {
            LoginResponsePacket loginResponsePacket = (LoginResponsePacket) decodePacket;

            if (loginResponsePacket.isSuccess()) {
                LoginUtil.markAsLogin(ctx.channel());
                System.out.println(new Date() + ":客户端登录成功");
            } else {
                System.out.println(new Date() + ":客户端登录失败，原因：" + loginResponsePacket.getReason());
            }
        } else if (decodePacket instanceof MessageRsponsePacket) {
            MessageRsponsePacket messageRsponsePacket = (MessageRsponsePacket) decodePacket;
            String message = messageRsponsePacket.getMessage();
            System.out.println(new Date() + ":服务端回复：" + message);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date() + ": 客户端登录中....");
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUsername("flash");
        loginRequestPacket.setPassword("pwd");
        ByteBufAllocator alloc = ctx.alloc();
        ByteBuf encode = PacketCodeC.INSTANCE.encode(alloc, loginRequestPacket);
        ctx.channel().writeAndFlush(encode);
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        ByteBuf buffer = ctx.alloc().buffer();
        byte[] bytes = "你好哇".getBytes(Charset.forName("utf-8"));
        buffer.writeBytes(bytes);
        return buffer;
    }
}
