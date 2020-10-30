package com.my.qs.nettydemo.handler.client;

import com.my.qs.nettydemo.protocol.MessageRequestPacket;
import com.my.qs.nettydemo.protocol.MessageResponsePacket;
import com.my.qs.nettydemo.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.text.SimpleDateFormat;

@ChannelHandler.Sharable
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    public static final MessageRequestHandler INSTANCE = new MessageRequestHandler();

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageRequestPacket messageRequestPacket) throws Exception {

        Channel channel = SessionUtil.getChannel(messageRequestPacket.getTo());

        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        MessageResponsePacket responsePacket = messageResponsePacket.setFrom(SessionUtil.getSession(channelHandlerContext.channel()).getUserName())
                .setMessage(messageRequestPacket.getMessage());
        if (channel == null || !SessionUtil.hasLogin(channel)){
            MessageResponsePacket messageResponse = new MessageResponsePacket();
            messageResponse.setMessage("消息未发送成功，对方不在线")
                    .setFrom("SYSTEM");
            channelHandlerContext.channel().writeAndFlush(messageResponse);
            return;
        }
        channel.writeAndFlush(responsePacket);

    }

    private MessageResponsePacket readMessage(MessageRequestPacket messageRequestPacket){
        System.out.println(messageRequestPacket);

        return null;
    }
}
