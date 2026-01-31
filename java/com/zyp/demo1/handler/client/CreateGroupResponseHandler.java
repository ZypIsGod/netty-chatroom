package com.zyp.demo1.handler.client;

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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Date:2026/1/31
 * @Author：zyp
 * @Description:
 */
public class CreateGroupResponseHandler extends SimpleChannelInboundHandler<CreateGroupResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupResponsePacket msg) throws Exception {
        System.out.println("你已经被拉群，群里有：" + msg.getUserNameList());
    }
}
