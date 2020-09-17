package com.my.qs.nettydemo.protocol;

import com.my.qs.nettydemo.pojo.Session;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LoginResponsePacket extends Packet{

    private Session session;

    private int code;

    private String message;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }

}

