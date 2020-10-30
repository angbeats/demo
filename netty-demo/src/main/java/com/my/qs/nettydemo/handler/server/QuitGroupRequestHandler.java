package com.my.qs.nettydemo.handler.server;

import com.my.qs.nettydemo.protocol.QuitGroupRequestPacket;
import com.my.qs.nettydemo.protocol.QuitGroupResponsePacket;
import com.my.qs.nettydemo.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * @description:
 * @author: angbeats
 * @create: 2020-10-30 11:06
 **/

@ChannelHandler.Sharable
public class QuitGroupRequestHandler extends SimpleChannelInboundHandler<QuitGroupRequestPacket> {

    public static final QuitGroupRequestHandler INSTANCE = new QuitGroupRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, QuitGroupRequestPacket quitGroupRequestPacket) throws Exception {
        String groupId = quitGroupRequestPacket.getGroupId();
        ChannelGroup group = SessionUtil.getGroup(groupId);
        QuitGroupResponsePacket quitGroupResponsePacket = new QuitGroupResponsePacket();
        if (group != null){
            quitGroupResponsePacket.setGroupId(groupId);
            quitGroupResponsePacket.setSuccess(true);
            group.remove(channelHandlerContext.channel());
        } else {
          quitGroupResponsePacket.setSuccess(false);
        }

        channelHandlerContext.writeAndFlush(quitGroupResponsePacket);
    }
}