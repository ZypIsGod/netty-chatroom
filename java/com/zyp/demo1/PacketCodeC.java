package com.zyp.demo1;

import com.zyp.demo1.tools.JSONSerializer;
import com.zyp.demo1.tools.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;


/**
 * @Date:2026/1/28
 * @Author：zyp
 * @Description:
 */
public class PacketCodeC {

    private static final int MAGIC_NUMBER = 0x12345678;

    public ByteBuf encode(Packet packet) {
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();
        byte[] bytes = Serializer.DEFAULT.serializer(packet);

        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommon());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);

        return byteBuf;
    }

    public Packet decode(ByteBuf byteBuf) {
        //跳过魔数
        byteBuf.skipBytes(4);
        //跳过版本号
        byteBuf.skipBytes(1);
        byte serializerAlgorithm = byteBuf.readByte();
        //指令
        byte common = byteBuf.readByte();
        //数据包长度
        int length = byteBuf.readInt();
        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);
        Class<? extends Packet> requstType = getRequstType(common);
        Serializer serializer = getSerializer(serializerAlgorithm);

        if (requstType != null && serializer != null) {
            return serializer.deserialize(requstType, bytes);
        }
        return null;
    }

    public Class<? extends Packet> getRequstType(byte common) {

        return LoginRequestPacket.class;
    }

    public Serializer getSerializer(byte serializerAlgorithm) {

        return new JSONSerializer();
    }
}
