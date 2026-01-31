package com.zyp.demo1.common;

import com.zyp.demo1.request.MessageReqeustPacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @Date:2026/1/31
 * @Author：zyp
 * @Description:
 */
public class SendToUserConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner sc, Channel channel) {
        System.out.println("输入UserId发送消息：");
        String userId = sc.nextLine();
        System.out.println("输入消息发送消息：");
        String line = sc.nextLine();
        MessageReqeustPacket messageReqeustPacket = new MessageReqeustPacket();
        messageReqeustPacket.setMessage(line);
        messageReqeustPacket.setToUserId(userId);
        ByteBuf byteBuf = PacketCodeC.INSTANCE.encode(channel.alloc(), messageReqeustPacket);
        channel.writeAndFlush(byteBuf);
    }
}
