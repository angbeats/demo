package com.my.qs.nettydemo;

import com.my.qs.nettydemo.handler.PacketSpliter;
import com.my.qs.nettydemo.handler.client.MessageRequestHandler;
import com.my.qs.nettydemo.handler.server.*;
import com.my.qs.nettydemo.handler.PacketDecoder;
import com.my.qs.nettydemo.handler.PacketEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Server {

    public static void main(String[] args) {
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup workers = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(boss, workers)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        nioSocketChannel.pipeline()
                                .addLast(new PacketSpliter())
                                .addLast(new PacketDecoder())
                                .addLast(LoginServerHandler.INSTANCE)
                                .addLast(AuthHandler.INSTANCE)
                                .addLast(MessageRequestHandler.INSTANCE)
                                .addLast(CreateGroupRequestHandler.INSTANCE)
                                .addLast(QuitGroupRequestHandler.INSTANCE)
                                .addLast(GroupMessageRequestHandler.INSTANCE)
                                .addLast(new PacketEncoder());
                    }
                });

        serverBootstrap.bind(8000);

    }
}
