package com.my.qs.nettydemo.console;

import com.my.qs.nettydemo.protocol.QuitGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @description:
 * @author: angbeats
 * @create: 2020-10-30 11:20
 **/

public class QuitGroupCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("请输入要退出的群聊id");
        String groupId = scanner.nextLine();
        QuitGroupRequestPacket quitGroupRequestPacket = new QuitGroupRequestPacket();
        quitGroupRequestPacket.setGroupId(groupId);
        channel.writeAndFlush(quitGroupRequestPacket);
    }
}