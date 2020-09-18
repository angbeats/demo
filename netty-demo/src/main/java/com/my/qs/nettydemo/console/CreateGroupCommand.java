package com.my.qs.nettydemo.console;

import com.my.qs.nettydemo.protocol.CreateGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Arrays;
import java.util.Scanner;

public class CreateGroupCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("请输入组名：");
        String groupName = scanner.nextLine();
        System.out.println();

        System.out.print("请输入群聊成员的id：");
        String members = scanner.nextLine();
        System.out.println();

        CreateGroupRequestPacket packet = new CreateGroupRequestPacket();
        packet.setGroupName(groupName)
                .setMembers(Arrays.asList(members.split(",")));

        channel.writeAndFlush(packet);
    }
}
