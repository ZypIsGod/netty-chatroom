package com.zyp.demo1.handler;

import com.zyp.demo1.LoginRequestPacket;
import com.zyp.demo1.Packet;
import com.zyp.demo1.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @Date:2026/1/27
 * @Author：zyp
 * @Description:
 */
public class FirstServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        Packet decodePacket = PacketCodeC.INSTANCE.decode(byteBuf);

        if (decodePacket instanceof LoginRequestPacket) {
            LoginRequestPacket loginRequestPacket = (LoginRequestPacket) decodePacket;

            if(valid(loginRequestPacket)) {
                //校验成功
            } else {
                //校验失败！
            }
        }

    }


    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        ByteBuf buffer = ctx.alloc().buffer();
        byte[] bytes = "服务端给你数据".getBytes(StandardCharsets.UTF_8);
        buffer.writeBytes(bytes);
        return buffer;
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }
}
