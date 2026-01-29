package com.zyp.demo1.handler.server;

import com.zyp.demo1.common.PacketCodeC;
import com.zyp.demo1.common.PacketCodeC2;
import com.zyp.demo1.request.LoginRequestPacket;
import com.zyp.demo1.response.LoginResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

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
        encodeByte = PacketCodeC2.INSTANCE.encode(ctx.alloc(), loginResponsePacket);
        ctx.channel().writeAndFlush(encodeByte);
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }
}
