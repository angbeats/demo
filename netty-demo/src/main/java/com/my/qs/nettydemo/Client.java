package com.my.qs.nettydemo;

import com.my.qs.nettydemo.handler.client.LoginClientHandler;
import com.my.qs.nettydemo.handler.PacketDecoder;
import com.my.qs.nettydemo.handler.PacketEncoder;
import com.my.qs.nettydemo.handler.client.MessageRequestHandler;
import com.my.qs.nettydemo.handler.server.MessageResponseHandler;
import com.my.qs.nettydemo.protocol.MessageRequestPacket;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        NioEventLoopGroup workers = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workers)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new PacketDecoder())
                                .addLast(new LoginClientHandler())
                                .addLast(new MessageResponseHandler())
                                .addLast(new PacketEncoder());


                    }


                });

        bootstrap.connect("localhost", 8000).addListener(new GenericFutureListener<Future<? super Void>>() {
            public void operationComplete(Future<? super Void> future) throws Exception {
                if (future.isSuccess()){
                    System.out.println("连接成功");
                    Channel channel = ((ChannelFuture) future).channel();
                    startConsole(channel);
                } else {
                    System.out.println("连接失败");
                }
            }
        });
    }


    private static void startConsole(Channel channel){
        new Thread(() -> {
            while (!Thread.interrupted()){
                Scanner scanner = new Scanner(System.in);
                String message = scanner.nextLine();

                MessageRequestPacket requestPacket = new MessageRequestPacket();
                requestPacket.setMessage(message);
                channel.writeAndFlush(requestPacket);
            }
        }).start();
    }
}
