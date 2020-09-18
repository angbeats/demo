package com.my.qs.nettydemo.console;

import com.my.qs.nettydemo.protocol.MessageRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

public class SendToUserCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("请输入要发送的消息:");
        String message = scanner.nextLine();
        System.out.print("请输入要发送的人:");
        String to = scanner.nextLine();

        MessageRequestPacket requestPacket = new MessageRequestPacket();
        requestPacket.setMessage(message)
                .setTo(to);
        channel.writeAndFlush(requestPacket);

    }
}
