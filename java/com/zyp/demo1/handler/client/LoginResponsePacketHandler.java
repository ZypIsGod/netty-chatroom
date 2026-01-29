package com.zyp.demo1.handler.client;

import com.zyp.demo1.response.LoginResponsePacket;
import com.zyp.demo1.tools.LoginUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

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
}
