package com.my.qs.nettydemo.protocol;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description:
 * @author: angbeats
 * @create: 2020-10-30 11:33
 **/

@Data
@Accessors(chain = true)
public class GroupMessageResponsePacket extends Packet {

    private String fromUser;

    private String groupId;

    private String message;

    @Override
    public Byte getCommand() {
        return Command.GroupMessageResponse;
    }
}