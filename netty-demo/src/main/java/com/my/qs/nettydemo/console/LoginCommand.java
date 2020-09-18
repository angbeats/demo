package com.my.qs.nettydemo.console;

import com.my.qs.nettydemo.protocol.LoginRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

public class LoginCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("请输入用户名：");
        String username = scanner.nextLine();

        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUsername(username)
                .setPassword(null);

        channel.writeAndFlush(loginRequestPacket);
    }
}
