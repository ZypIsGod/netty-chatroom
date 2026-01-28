package com.zyp.demo1.response;

import com.zyp.demo1.Command;
import com.zyp.demo1.Packet;
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

    @Override
    public Byte getCommon() {
        return Command.LOGIN_RESPONSE;
    }
}
