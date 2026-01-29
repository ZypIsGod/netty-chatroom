package com.zyp.demo1.handler.client;

import com.zyp.demo1.response.MessageRsponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * @Date:2026/1/29
 * @Author：zyp
 * @Description:
 */
public class MessageRsponsePacketHandler extends SimpleChannelInboundHandler<MessageRsponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRsponsePacket msg) throws Exception {
        String message = msg.getMessage();
        System.out.println(new Date() + ":服务端回复：" + message);
    }
}
