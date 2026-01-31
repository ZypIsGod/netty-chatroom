package com.zyp.demo1.common;

import io.netty.channel.Channel;

import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Date:2026/1/31
 * @Author：zyp
 * @Description:
 */
public class ConsoleManager implements ConsoleCommand {

    private Map<Integer, ConsoleCommand> controlMap = new ConcurrentHashMap<>();

    public ConsoleManager() {
        controlMap.put(1, new SendToUserConsoleCommand());
        controlMap.put(2, new CreateGroupConsoleCommand());
        controlMap.put(3, new LogoutConsoleCommand());

    }

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("请输入以下操作对应序号：");
        System.out.println("1-发送消息给用户；");
        System.out.println("2-创建群聊；");
        System.out.println("3-登出；");

        ConsoleCommand consoleCommand = controlMap.get(scanner.nextInt());

        if (consoleCommand != null) {
            consoleCommand.exec(scanner, channel);
        } else {
            System.out.println("指令输入有误，请正确输入指令！");
        }
    }
}
