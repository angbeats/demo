package com.my.qs.nettydemo;

import com.my.qs.nettydemo.console.ConsoleCommandManager;
import com.my.qs.nettydemo.handler.PacketSpliter;
import com.my.qs.nettydemo.handler.client.CreateGroupResponseHandler;
import com.my.qs.nettydemo.handler.client.LoginClientHandler;
import com.my.qs.nettydemo.handler.PacketDecoder;
import com.my.qs.nettydemo.handler.PacketEncoder;
import com.my.qs.nettydemo.handler.client.MessageRequestHandler;
import com.my.qs.nettydemo.handler.client.QuitGroupResponseHandler;
import com.my.qs.nettydemo.handler.server.GroupMessageResponseHandler;
import com.my.qs.nettydemo.handler.server.MessageResponseHandler;
import com.my.qs.nettydemo.protocol.LoginRequestPacket;
import com.my.qs.nettydemo.protocol.MessageRequestPacket;
import com.my.qs.nettydemo.util.SessionUtil;
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
                        socketChannel.pipeline()
                                .addLast(new PacketSpliter())
                                .addLast(new PacketDecoder())
                                .addLast(LoginClientHandler.INSTANCE)
                                .addLast(MessageResponseHandler.INSTANCE)
                                .addLast(CreateGroupResponseHandler.INSTANCE)
                                .addLast(QuitGroupResponseHandler.INSTANCE)
                                .addLast(GroupMessageResponseHandler.INSTANCE)
                                .addLast(new PacketEncoder());


                    }


                });

        bootstrap.connect("localhost", 8000).addListener(new GenericFutureListener<Future<? super Void>>() {
            public void operationComplete(Future<? super Void> future) throws Exception {
                if (future.isSuccess()){
                    System.out.println("连接成功");
                    Channel channel = ((ChannelFuture) future).channel();
                    LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("请输入用户名：");
                    String username = scanner.nextLine();
                    loginRequestPacket.setUsername(username);
                    channel.writeAndFlush(loginRequestPacket);
                    startConsole(channel);
                } else {
                    System.out.println("连接失败");
                }
            }
        });
    }


    private static void startConsole(Channel channel){
        new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            ConsoleCommandManager commandManager = new ConsoleCommandManager();
            while (!Thread.interrupted()){
                if (!SessionUtil.hasLogin(channel)){
                    System.out.println("登录失效或还未登录");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else {
                    commandManager.exec(scanner, channel);
                }

            }
        }).start();
    }
}
