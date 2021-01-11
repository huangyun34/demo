package com.my.netty.splicing.linebase;

import com.my.netty.splicing.CommonConstants;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;

public class LineBaseServer {

    public static void main(String[] args) throws InterruptedException {
        new LineBaseServer().start();
    }

    private void start() throws InterruptedException {
        final EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap()
                    .group(eventLoopGroup, eventLoopGroup)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(CommonConstants.SERVER_PORT)
                    .childHandler(new ChannelInitializerImp());
            ChannelFuture channelFuture = serverBootstrap.bind().sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            eventLoopGroup.shutdownGracefully().sync();
        }
    }

    private class ChannelInitializerImp extends ChannelInitializer<Channel> {

        @Override
        protected void initChannel(Channel channel) throws Exception {
            channel.pipeline()
                    .addLast(new LineBasedFrameDecoder(1024))
                    .addLast(new LineBaseServerHandler());
        }
    }
}
