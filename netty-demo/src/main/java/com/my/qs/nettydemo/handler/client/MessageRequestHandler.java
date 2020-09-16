package com.my.qs.nettydemo.handler.client;

import com.my.qs.nettydemo.protocol.MessageRequestPacket;
import com.my.qs.nettydemo.protocol.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageRequestPacket messageRequestPacket) throws Exception {
        MessageResponsePacket responsePacket = readMessage(messageRequestPacket);

        channelHandlerContext.channel().writeAndFlush(responsePacket);
    }

    private MessageResponsePacket readMessage(MessageRequestPacket messageRequestPacket){
        System.out.println(messageRequestPacket);

        MessageResponsePacket responsePacket = new MessageResponsePacket();
        responsePacket.setCode(200)
                .setMessage("消息接收成功" + simpleDateFormat.format(new Date()));
        return responsePacket;
    }
}
