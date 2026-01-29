package com.zyp.demo1.common;

import com.zyp.demo1.common.Packet;
import com.zyp.demo1.common.PacketCodeC;
import com.zyp.demo1.common.PacketCodeC2;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @Date:2026/1/29
 * @Author：zyp
 * @Description:
 */
public class PacketEndcoder extends MessageToByteEncoder<Packet> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Packet packet, ByteBuf out) throws Exception {
        PacketCodeC2.INSTANCE.encode(out,packet);
    }
}
