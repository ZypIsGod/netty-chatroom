package com.zyp.demo1.response;

import com.zyp.demo1.common.Command;
import com.zyp.demo1.common.Packet;
import lombok.Data;

import java.util.List;

/**
 * @Date:2026/1/31
 * @Author：zyp
 * @Description:
 */
@Data
public class CreateGroupResponsePacket extends Packet {

    /**
     * 拉群
     */
    private List<String> userList;

    /**
     * 群组id
     */
    private String groupId;

    @Override
    public Byte getCommon() {
        return Command.CREATE_GROUP_RESPONSE;
    }
}
