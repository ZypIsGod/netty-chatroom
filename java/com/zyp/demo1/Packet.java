package com.zyp.demo1;

import io.netty.buffer.ByteBuf;
import lombok.Data;

/**
 * @Date:2026/1/28
 * @Author：zyp
 * @Description:
 */
@Data
public abstract class Packet {

    private Byte version = 1;


    public abstract Byte getCommon();
}
