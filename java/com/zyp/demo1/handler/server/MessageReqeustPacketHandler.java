package com.zyp.demo1.handler.server;

import com.alibaba.fastjson.JSON;
import com.zyp.demo1.common.PacketCodeC;
import com.zyp.demo1.common.PacketCodeC2;
import com.zyp.demo1.pojo.Session;
import com.zyp.demo1.request.MessageReqeustPacket;
import com.zyp.demo1.response.MessageRsponsePacket;
import com.zyp.demo1.tools.SessionUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
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
        System.out.println(new Date() + ":收到客户端消息：" + JSON.toJSONString(msg));
        //发送者
        Session session = SessionUtil.getSession(ctx.channel());
        //接收者通道
        Channel channel = SessionUtil.getChannel(msg.getToUserId());
        MessageRsponsePacket messageRsponsePacket = new MessageRsponsePacket();
        messageRsponsePacket.setMessage(msg.getMessage());
        messageRsponsePacket.setFromUserId(session.getUserId());
        messageRsponsePacket.setFromUsername(session.getUserName());
        if (channel != null) {
            ByteBuf encodeByte = PacketCodeC2.INSTANCE.encode(channel.alloc(), messageRsponsePacket);
            channel.writeAndFlush(encodeByte);
        } else {
            System.out.println(new Date() + ":" + msg.getToUserId() + ":已经离线");
        }

    }
}
