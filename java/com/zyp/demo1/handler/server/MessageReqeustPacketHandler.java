package com.zyp.demo1.handler.server;

import com.zyp.demo1.common.PacketCodeC;
import com.zyp.demo1.common.PacketCodeC2;
import com.zyp.demo1.request.MessageReqeustPacket;
import com.zyp.demo1.response.MessageRsponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * @Date:2026/1/29
 * @Author：zyp
 * @Description:
 */
public class MessageReqeustPacketHandler extends SimpleChannelInboundHandler<MessageReqeustPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageReqeustPacket msg) throws Exception {
        System.out.println(new Date() + ":收到客户端消息：" + msg.getMessage());
        MessageRsponsePacket messageRsponsePacket = new MessageRsponsePacket();
        messageRsponsePacket.setMessage("服务端回复：【" + msg.getMessage() + "】");
        ByteBuf encodeByte = PacketCodeC2.INSTANCE.encode(ctx.alloc(), messageRsponsePacket);
        ctx.channel().writeAndFlush(encodeByte);
    }
}
