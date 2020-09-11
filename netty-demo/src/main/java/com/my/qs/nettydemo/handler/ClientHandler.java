package com.my.qs.nettydemo.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;

public class ClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        System.out.println("客户端写出数据");

        ByteBuf byteBuf = getByteBuf(ctx);

        ctx.channel().writeAndFlush(byteBuf);


    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;

        System.out.println("客户端接收到服务端回写的数据 -> " + byteBuf.toString(Charset.forName("utf-8")));
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx){

        ByteBuf buffer = ctx.alloc().buffer();
        byte[] bytes = "测试数据".getBytes(Charset.forName("utf-8"));

        buffer.writeBytes(bytes);

        return buffer;

    }
}
