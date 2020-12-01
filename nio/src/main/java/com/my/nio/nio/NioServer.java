package com.my.nio.nio;

import com.my.nio.Const;

/**
 * 类说明：nio通信服务端
 */
public class NioServer {

    public static void main(String[] args) {
        new Thread(new NioServerHandle(Const.DEFAULT_PORT), "server").start();
    }
}
