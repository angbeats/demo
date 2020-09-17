package com.my.qs.nettydemo.handler.server;

import com.my.qs.nettydemo.pojo.Session;
import com.my.qs.nettydemo.protocol.LoginRequestPacket;
import com.my.qs.nettydemo.protocol.LoginResponsePacket;
import com.my.qs.nettydemo.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

public class LoginServerHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginRequestPacket loginRequestPacket) throws Exception {
            channelHandlerContext.channel().writeAndFlush(login(loginRequestPacket, channelHandlerContext));
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        SessionUtil.unBindSession(ctx.channel());
        super.channelInactive(ctx);
    }

    private LoginResponsePacket login(LoginRequestPacket loginRequestPacket, ChannelHandlerContext channelHandlerContext) {
        Session session = new Session();
        session.setUserId(UUID.randomUUID().toString().substring(0, 7))
                .setUserName(loginRequestPacket.getUsername());

        System.out.println(session + "登录成功");

        SessionUtil.bindSession(session, channelHandlerContext.channel());

        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setCode(200)
                .setMessage("登录成功")
                .setSession(session);

        return loginResponsePacket;
    }
}
