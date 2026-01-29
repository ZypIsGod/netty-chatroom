package com.zyp.demo1.common;

import com.zyp.demo1.common.PacketCodeC2;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @Date:2026/1/29
 * @Author：zyp
 * @Description:
 */
public class PacketDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> out) throws Exception {
        out.add(PacketCodeC2.INSTANCE.decode(byteBuf));
    }
}
