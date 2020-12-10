package com.my.qs.nettydemo.handler;

import com.my.qs.nettydemo.handler.client.MessageRequestHandler;
import com.my.qs.nettydemo.handler.server.CreateGroupRequestHandler;
import com.my.qs.nettydemo.handler.server.GroupMessageRequestHandler;
import com.my.qs.nettydemo.handler.server.LoginServerHandler;
import com.my.qs.nettydemo.handler.server.QuitGroupRequestHandler;
import com.my.qs.nettydemo.protocol.Command;
import com.my.qs.nettydemo.protocol.LoginRequestPacket;
import com.my.qs.nettydemo.protocol.Packet;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: angbeats
 * @create: 2020-11-11 17:15
 **/

@ChannelHandler.Sharable
public class IMServerHandler extends SimpleChannelInboundHandler<Packet> {

    public static final IMServerHandler INSTANCE = new IMServerHandler();

    private Map<Byte, SimpleChannelInboundHandler<? extends Packet>> handlerMap;

    private IMServerHandler(){
        handlerMap = new HashMap<>();

        handlerMap.put(Command.MESSAGE_REQUEST, MessageRequestHandler.INSTANCE);
        handlerMap.put(Command.GROUP_MESSAGE_REQUEST, GroupMessageRequestHandler.INSTANCE);
        handlerMap.put(Command.CREATE_GROUP_REQUEST, CreateGroupRequestHandler.INSTANCE);
        handlerMap.put(Command.QUIT_GROUP_REQUEST, QuitGroupRequestHandler.INSTANCE);

    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Packet packet) throws Exception {
        handlerMap.get(packet.getCommand()).channelRead(channelHandlerContext, packet);
    }
}