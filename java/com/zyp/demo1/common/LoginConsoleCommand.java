package com.zyp.demo1.common;

import com.zyp.demo1.request.LoginRequestPacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @Date:2026/1/31
 * @Author：zyp
 * @Description:
 */
public class LoginConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner sc, Channel channel) {
        //登录
        System.out.println("请先登录：");
        String userName = sc.nextLine();
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUsername(userName);
        loginRequestPacket.setPassword("pwd");
        ByteBuf byteBuf = PacketCodeC.INSTANCE.encode(channel.alloc(), loginRequestPacket);
        channel.writeAndFlush(byteBuf);

    }
}
