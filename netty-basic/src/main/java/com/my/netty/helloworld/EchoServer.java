package com.my.netty.helloworld;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class EchoServer {

    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws InterruptedException {
        EchoServer echoServer = new EchoServer(9999);
        System.out.println("服务器即将启动");
        echoServer.start();
        System.out.println("服务器关闭");
    }


    public void start() throws InterruptedException {
        final EchoServerHandler echoServerHandler = new EchoServerHandler();

        /*线程组*/
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        try {
            /*服务端启动必备*/
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(eventLoopGroup)
                    .channel(NioServerSocketChannel.class) /*指定使用NIO的通信模式*/
                    .localAddress(port)/*指定监听端口*/
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel sch) {
                            sch.pipeline().addLast(echoServerHandler);
                        }
                    });
            /*异步绑定到服务器，sync()会阻塞到完成*/
            ChannelFuture channelFuture = serverBootstrap.bind().sync();
            /*阻塞当前线程，直到服务器的ServerChannel被关闭*/
            channelFuture.channel().closeFuture().sync();
        } finally {
            eventLoopGroup.shutdownGracefully().sync();
        }
    }
}
