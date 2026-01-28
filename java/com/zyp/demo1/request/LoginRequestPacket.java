package com.zyp.demo1.request;

import com.zyp.demo1.common.Command;
import com.zyp.demo1.Packet;
import lombok.Data;

/**
 * @Date:2026/1/28
 * @Author：zyp
 * @Description:
 */
@Data
public class LoginRequestPacket extends Packet {
    private String userId;

    private String username;

    private String password;

    @Override
    public Byte getCommon() {
        return Command.LOGIN_REQUEST;
    }
}
