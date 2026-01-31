package com.zyp.demo1.common;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @Date:2026/1/31
 * @Author：zyp
 * @Description:
 */
public interface ConsoleCommand {

    /**
     * 控制台执行
     * @param type 指令
     * @param channel 通道
     */
    void exec(Scanner scanner, Channel channel);

}
