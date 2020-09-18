package com.my.qs.nettydemo.protocol;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class CreateGroupResponsePacket extends Packet {

    private String groupId;

    private List<String> members;

    @Override
    public Byte getCommand() {
        return Command.CREATE_GROUP_RESPONSE;
    }
}
