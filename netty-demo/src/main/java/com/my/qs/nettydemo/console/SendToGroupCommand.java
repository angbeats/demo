package com.my.qs.nettydemo.console;

import com.my.qs.nettydemo.protocol.GroupMessageRequestPacket;
import com.my.qs.nettydemo.util.SessionUtil;
import io.netty.channel.Channel;

import java.util.Scanner;

public class SendToGroupCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("请输入群聊的组名：");
        String group = scanner.nextLine();
        System.out.print("请输入发送的消息：");
        String message = scanner.nextLine();

        GroupMessageRequestPacket groupMessageRequestPacket = new GroupMessageRequestPacket();
        groupMessageRequestPacket.setFromUser(SessionUtil.getSession(channel).getUserName())
                .setGroupId(group)
                .setMessage(message);

        channel.writeAndFlush(groupMessageRequestPacket);
    }
}
