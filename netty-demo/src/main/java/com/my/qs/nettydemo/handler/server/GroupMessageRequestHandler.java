package com.my.qs.nettydemo.handler.server;

import com.my.qs.nettydemo.protocol.GroupMessageRequestPacket;
import com.my.qs.nettydemo.protocol.GroupMessageResponsePacket;
import com.my.qs.nettydemo.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * @description:
 * @author: angbeats
 * @create: 2020-10-30 11:34
 **/

@ChannelHandler.Sharable
public class GroupMessageRequestHandler extends SimpleChannelInboundHandler<GroupMessageRequestPacket> {

    public static final GroupMessageRequestHandler INSTANCE = new GroupMessageRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, GroupMessageRequestPacket groupMessageRequestPacket) throws Exception {
        String groupId = groupMessageRequestPacket.getGroupId();
        ChannelGroup group = SessionUtil.getGroup(groupId);

        GroupMessageResponsePacket groupMessageResponsePacket = new GroupMessageResponsePacket();
        groupMessageResponsePacket.setFromUser(groupMessageRequestPacket.getFromUser())
                .setMessage(groupMessageRequestPacket.getMessage())
                .setGroupId(groupId);

        group.writeAndFlush(groupMessageResponsePacket);

    }
}