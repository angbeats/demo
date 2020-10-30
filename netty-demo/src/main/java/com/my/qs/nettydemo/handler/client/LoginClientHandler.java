package com.my.qs.nettydemo.handler.client;

import com.my.qs.nettydemo.Attributes;
import com.my.qs.nettydemo.protocol.LoginResponsePacket;
import com.my.qs.nettydemo.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class LoginClientHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    public static final LoginClientHandler INSTANCE = new LoginClientHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginResponsePacket loginResponsePacket) throws Exception {
        if(loginResponsePacket.getCode() == 200){
            System.out.println(loginResponsePacket.getMessage());

            channelHandlerContext.channel().attr(Attributes.SESSION);
            SessionUtil.bindSession(loginResponsePacket.getSession(), channelHandlerContext.channel());
        }
    }
}
