package com.my.qs.nettydemo.handler.server;

import com.my.qs.nettydemo.PacketCodeC;
import com.my.qs.nettydemo.protocol.LoginRequestPacket;
import com.my.qs.nettydemo.protocol.MessageRequestPacket;
import com.my.qs.nettydemo.protocol.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;

public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        Packet decodePacket = PacketCodeC.INSTANCE.decode(byteBuf);

        if (decodePacket instanceof LoginRequestPacket){
            System.out.println("登录请求");
            ByteBuf msg1 = getByteBuf(ctx);
            ctx.channel().writeAndFlush(msg1);
        }else if (decodePacket instanceof MessageRequestPacket){
            System.out.println("服务端读到的数据 -> " + decodePacket);
            ByteBuf msg1 = getByteBuf(ctx);
            ctx.channel().writeAndFlush(msg1);
        } else {

        }



    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx){
        ByteBuf buffer = ctx.alloc().buffer();
        byte[] bytes = "服务端回写数据".getBytes(Charset.forName("utf-8"));
        buffer.writeBytes(bytes);
        return buffer;
    }
}
