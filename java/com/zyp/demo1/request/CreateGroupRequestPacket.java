package com.zyp.demo1.request;

import com.zyp.demo1.common.Packet;
import lombok.Data;

/**
 * @Date:2026/1/31
 * @Author：zyp
 * @Description:
 */
@Data
public class CreateGroupRequestPacket extends Packet {
    @Override
    public Byte getCommon() {
        return null;
    }
}
