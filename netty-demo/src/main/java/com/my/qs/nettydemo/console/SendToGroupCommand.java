package com.my.qs.nettydemo.console;

import io.netty.channel.Channel;

import java.util.Scanner;

public class SendToGroupCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("请输入群聊的组名：");
        String group = scanner.nextLine();
        System.out.print("请输入发送的消息：");
        String message = scanner.nextLine();
    }
}
