package com.my.qs.nettydemo;

import com.my.qs.nettydemo.handler.ClientHandler;
import com.my.qs.nettydemo.handler.LoginClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

public class Client {

    public static void main(String[] args) {
        NioEventLoopGroup workers = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workers)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new LoginClientHandler());
                    }


                });

        bootstrap.connect("localhost", 8000).addListener(new GenericFutureListener<Future<? super Void>>() {
            public void operationComplete(Future<? super Void> future) throws Exception {
                if (future.isSuccess()){
                    System.out.println("连接成功");
                } else {
                    System.out.println("连接失败");
                }
            }
        });
    }
}
