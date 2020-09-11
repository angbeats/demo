package com.my.qs.nettydemo.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;

public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;

        System.out.println("服务端读到的数据 -> " + byteBuf.toString(Charset.forName("utf-8")));

        ByteBuf msg1 = getByteBuf(ctx);
        ctx.channel().writeAndFlush(msg1);
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx){
        ByteBuf buffer = ctx.alloc().buffer();
        byte[] bytes = "服务端回写数据".getBytes(Charset.forName("utf-8"));
        buffer.writeBytes(bytes);
        return buffer;
    }
}
