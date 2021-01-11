package com.my.netty.splicing.linebase;

import com.my.netty.splicing.CommonConstants;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;

import java.net.InetSocketAddress;

public class LineBaseClient {

    public static void main(String[] args) throws InterruptedException {
        new LineBaseClient().start();
    }

    public void start() throws InterruptedException {
        final EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap()
                    .group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(CommonConstants.SERVER_PORT))
                    .handler(new ChannelInitializerImp());
            ChannelFuture channelFuture = bootstrap.connect().sync();
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
                    .addLast(new LineBaseClientHandler());
        }
    }
}
