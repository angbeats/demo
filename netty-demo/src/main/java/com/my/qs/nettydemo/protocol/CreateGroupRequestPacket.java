package com.my.qs.nettydemo.protocol;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class CreateGroupRequestPacket extends Packet {

    private String groupName;

    private List<String> members;

    @Override
    public Byte getCommand() {
        return Command.CREATE_GROUP_REQUEST;
    }
}
