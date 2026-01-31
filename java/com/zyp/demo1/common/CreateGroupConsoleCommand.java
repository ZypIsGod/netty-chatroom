package com.zyp.demo1.common;

import com.zyp.demo1.request.CreateGroupRequestPacket;
import com.zyp.demo1.tools.SessionUtil;
import io.netty.channel.Channel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * @Date:2026/1/31
 * @Author：zyp
 * @Description:
 */
public class CreateGroupConsoleCommand  implements ConsoleCommand{
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("输入要被拉群的用户以逗号分割(,)");
        String s = scanner.next();
        String[] split = s.split(",");
        if(split == null || split.length == 0) {
            System.out.println("输入有误");
            return;
        }
        List<String> userIdList = new ArrayList<>();
        for(String userId : split) {
            userIdList.add(userId);
        }
        CreateGroupRequestPacket createGroupRequestPacket = new CreateGroupRequestPacket();
        createGroupRequestPacket.setUserList(userIdList);
        channel.writeAndFlush(PacketCodeC.INSTANCE.encode(channel.alloc(),createGroupRequestPacket));
    }
}
