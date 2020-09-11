package com.my.qs.nettydemo.handler;

import com.my.qs.nettydemo.PacketCodeC;
import com.my.qs.nettydemo.protocol.LoginRequestPacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class LoginClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(1);
        loginRequestPacket.setUsername("root");
        loginRequestPacket.setPassword("root");

        ByteBuf encode = PacketCodeC.INSTANCE.encode(ctx.alloc(), loginRequestPacket);


        ctx.channel().writeAndFlush(encode);
    }
}
