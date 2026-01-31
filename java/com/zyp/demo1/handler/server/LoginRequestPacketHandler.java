package com.zyp.demo1.handler.server;

import com.alibaba.fastjson.JSON;
import com.zyp.demo1.common.PacketCodeC;
import com.zyp.demo1.common.PacketCodeC2;
import com.zyp.demo1.pojo.Session;
import com.zyp.demo1.request.LoginRequestPacket;
import com.zyp.demo1.response.LoginResponsePacket;
import com.zyp.demo1.tools.SessionUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;
import java.util.UUID;

/**
 * @Date:2026/1/29
 * @Author：zyp
 * @Description:
 */
public class LoginRequestPacketHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket msg) throws Exception {
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        ByteBuf encodeByte = null;
        if (valid(msg)) {
            //校验成功
            loginResponsePacket.setSuccess(true);
        } else {
            //校验失败！
            loginResponsePacket.setSuccess(false);
        }
        //设置session
        Session session = new Session();
        session.setUserId(UUID.randomUUID().toString());
        session.setUserName(msg.getUsername());
        SessionUtil.bindSession(session,ctx.channel());
        System.out.println(new Date()+":登录信息："+ JSON.toJSONString(session));
        encodeByte = PacketCodeC2.INSTANCE.encode(ctx.alloc(), loginResponsePacket);
        ctx.channel().writeAndFlush(encodeByte);
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }
}
