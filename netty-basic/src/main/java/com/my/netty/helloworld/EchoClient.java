package com.my.netty.helloworld;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class EchoClient {

    public static void main(String[] args) throws InterruptedException {
        start();
    }

    public static void start() throws InterruptedException {
        final EchoClientHandler echoClientHandler = new EchoClientHandler();
        /*线程组*/
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        try {
            /*客户端启动必备*/
            Bootstrap bootstrap = new Bootstrap();

            bootstrap.group(eventLoopGroup)
                    .channel(NioSocketChannel.class)/*指定使用NIO的通信模式*/
                    .remoteAddress("127.0.0.1", 9999)/*指定服务器的IP地址和端口*/
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) {
                            socketChannel.pipeline().addLast(echoClientHandler);
                        }
                    });
            /*异步连接到服务器，sync()会阻塞到完成*/
            ChannelFuture channelFuture = bootstrap.connect().sync();
            /*阻塞当前线程，直到客户端的Channel被关闭*/
            channelFuture.channel().closeFuture().sync();
        } finally {
            eventLoopGroup.shutdownGracefully().sync();
        }

    }
}
