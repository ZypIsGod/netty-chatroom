package com.zyp.demo1;

import lombok.Data;

/**
 * @Date:2026/1/28
 * @Author：zyp
 * @Description:
 */
@Data
public class LoginRequestPacket extends Packet{
    private Integer userId;

    private String username;

    private String password;

    @Override
    public Byte getCommon() {
        return Command.LOGIN_REQUEST;
    }
}
