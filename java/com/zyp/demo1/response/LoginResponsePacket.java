package com.zyp.demo1.response;

import com.zyp.demo1.common.Command;
import com.zyp.demo1.common.Packet;
import lombok.Data;

/**
 * @Date:2026/1/28
 * @Author：zyp
 * @Description:
 */
@Data
public class LoginResponsePacket extends Packet {

    private boolean success;

    private String reason;

    private String userId;

    @Override
    public Byte getCommon() {
        return Command.LOGIN_RESPONSE;
    }
}
