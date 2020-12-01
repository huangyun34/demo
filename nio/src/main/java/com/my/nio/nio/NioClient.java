package com.my.nio.nio;

import com.my.nio.Const;

import java.util.Scanner;

/**
 * 类说明：nio通信客户端
 */
public class NioClient {
    private static NioClientHandle nioClientHandle;

    public static void start() {
        nioClientHandle = new NioClientHandle(Const.DEFAULT_SERVER_IP, Const.DEFAULT_PORT);
        new Thread(nioClientHandle, "server").start();
    }

    //向服务器发送消息
    public static boolean sendMsg(String msg) throws Exception{
        nioClientHandle.sendMsg(msg);
        return true;
    }

    public static void main(String[] args) throws Exception {
        start();
        Scanner scanner = new Scanner(System.in);
        while(NioClient.sendMsg(scanner.next()));
    }
}
