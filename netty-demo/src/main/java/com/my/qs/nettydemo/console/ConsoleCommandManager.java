package com.my.qs.nettydemo.console;

import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConsoleCommandManager implements ConsoleCommand {

    private static final Map<String, ConsoleCommand> commandMap = new HashMap<>();

    static {
        commandMap.put("createGroup", new CreateGroupCommand());
        commandMap.put("login", new LoginCommand());
        commandMap.put("sendToUser", new SendToUserCommand());
        commandMap.put("quitGroup", new QuitGroupCommand());
        commandMap.put("sendToGroup", new SendToGroupCommand());
    }

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("=========================");
        System.out.print("请输入指令：");
        String command = scanner.nextLine();
        ConsoleCommand consoleCommand = commandMap.get(command);
        if (consoleCommand != null){
            consoleCommand.exec(scanner, channel);
        } else {
            System.out.println("指令无法被解析...请输入正确的指令");
            System.out.println("=========================");
        }
    }
}
