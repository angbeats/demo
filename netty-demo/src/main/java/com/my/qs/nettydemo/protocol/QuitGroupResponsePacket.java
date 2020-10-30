package com.my.qs.nettydemo.protocol;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description:
 * @author: angbeats
 * @create: 2020-10-30 11:03
 **/

@Data
@Accessors(chain = true)
public class QuitGroupResponsePacket extends Packet{

    private String groupId;

    private Boolean success;

    @Override
    public Byte getCommand() {
        return Command.QuitGroupResponse;
    }
}