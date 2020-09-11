package com.my.qs.nettydemo.handler;

import com.my.qs.nettydemo.PacketCodeC;
import com.my.qs.nettydemo.protocol.LoginRequestPacket;
import com.my.qs.nettydemo.protocol.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class LoginServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        Packet decode = PacketCodeC.INSTANCE.decode(byteBuf);

        if (decode instanceof LoginRequestPacket) {
            LoginRequestPacket loginRequestPacket = (LoginRequestPacket) decode;
            System.out.println(loginRequestPacket);
        }
    }
}
