package com.my.qs.nettydemo.handler.client;

import com.my.qs.nettydemo.protocol.QuitGroupResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @description:
 * @author: angbeats
 * @create: 2020-10-30 11:06
 **/

@ChannelHandler.Sharable
public class QuitGroupResponseHandler extends SimpleChannelInboundHandler<QuitGroupResponsePacket> {

    public static final QuitGroupResponseHandler INSTANCE = new QuitGroupResponseHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, QuitGroupResponsePacket quitGroupResponsePacket) throws Exception {
        if (quitGroupResponsePacket.getSuccess().equals(true)){
            System.out.println("退出群聊" + quitGroupResponsePacket.getGroupId() + "成功");
        } else {
            System.out.println("退出群聊失败");
        }
    }
}