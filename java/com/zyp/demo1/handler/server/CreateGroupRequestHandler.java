package com.zyp.demo1.handler.server;

import com.alibaba.fastjson.JSON;
import com.zyp.demo1.pojo.Session;
import com.zyp.demo1.request.CreateGroupRequestPacket;
import com.zyp.demo1.response.CreateGroupResponsePacket;
import com.zyp.demo1.tools.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.internal.StringUtil;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Date:2026/1/31
 * @Author：zyp
 * @Description:
 */
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket msg) throws Exception {
        Session session = SessionUtil.getSession(ctx.channel());
        ChannelGroup channelsGroup = new DefaultChannelGroup(ctx.executor());
        List<String> userNameList = new ArrayList<>();
        userNameList.add(session.getUserName());
        channelsGroup.add(SessionUtil.getChannel(session.getUserId()));
        for (String userId : msg.getUserList()) {
            String userName = SessionUtil.getSession(SessionUtil.getChannel(userId)).getUserName();
            if (!StringUtil.isNullOrEmpty(userName)) {
                channelsGroup.add(SessionUtil.getChannel(userId));
                userNameList.add(userName);
            }
        }
        CreateGroupResponsePacket createGroupResponsePacket = new CreateGroupResponsePacket();
        createGroupResponsePacket.setGroupId(UUID.randomUUID().toString());
        createGroupResponsePacket.setUserNameList(userNameList);
        createGroupResponsePacket.setIsSuccess(true);
        //发送
        channelsGroup.writeAndFlush(createGroupResponsePacket);
        System.out.println(new Date() + ":建群成功：" + JSON.toJSONString(createGroupResponsePacket));
    }
}
