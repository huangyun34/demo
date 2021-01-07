package com.my.netty.splicing.delimiter;

import com.my.netty.splicing.CommonConstants;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;

public class DelimiterEchoServer {

    private void start() throws InterruptedException {
        /*线程组*/
        final EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            /*服务端启动必须*/
            ServerBootstrap serverBootstrap = new ServerBootstrap()
                    /*将线程组传入*/
                    .group(eventLoopGroup, eventLoopGroup)
                    /*指定使用NIO进行网络传输*/
                    .channel(NioServerSocketChannel.class)
                    /*指定服务器监听端口*/
                    .localAddress(CommonConstants.SERVER_PORT)
                    /*服务端每接收到一个连接请求，就会新启一个socket通信，也就是channel，
                所以下面这段代码的作用就是为这个子channel增加handle*/
                    .childHandler(new ChannelInitializerImp());
            /*异步绑定到服务器，sync()会阻塞直到完成*/
            ChannelFuture channelFuture = serverBootstrap.bind().sync();
            System.out.println("服务器启动完成，等待客户端的连接和数据.....");
            /*阻塞直到服务器的channel关闭*/
            channelFuture.channel().closeFuture().sync();
        } finally {
            /*优雅关闭线程组*/
            eventLoopGroup.shutdownGracefully().sync();
        }
    }

    private class ChannelInitializerImp extends ChannelInitializer<Channel> {

        @Override
        protected void initChannel(Channel channel) throws Exception {
            ByteBuf byteBuf = Unpooled.copiedBuffer(CommonConstants.DELIMITER_SYMBOL.getBytes());
            channel.pipeline()
                    .addLast(new DelimiterBasedFrameDecoder(1024, byteBuf))
                    .addLast(new DelimiterServerHandler());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("服务器即将启动");
        new DelimiterEchoServer().start();
    }
}
