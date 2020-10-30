package com.my.qs.nettydemo.handler.server;

import com.my.qs.nettydemo.protocol.GroupMessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @description:
 * @author: angbeats
 * @create: 2020-10-30 11:34
 **/

public class GroupMessageResponseHandler extends SimpleChannelInboundHandler<GroupMessageResponsePacket> {

    public static final GroupMessageResponseHandler INSTANCE = new GroupMessageResponseHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, GroupMessageResponsePacket groupMessageResponsePacket) throws Exception {
        System.out.println(groupMessageResponsePacket.getGroupId() + "：" + groupMessageResponsePacket.getFromUser() +
                "说：" + groupMessageResponsePacket.getMessage() );
    }
}