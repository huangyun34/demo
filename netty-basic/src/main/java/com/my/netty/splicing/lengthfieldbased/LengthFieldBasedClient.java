package com.my.netty.splicing.lengthfieldbased;

import com.my.netty.splicing.CommonConstants;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

import java.net.InetSocketAddress;

public class LengthFieldBasedClient {
    public static void main(String[] args) throws InterruptedException {
         new LengthFieldBasedClient().start();
    }

    private void start() throws InterruptedException {
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
                    .addLast(new LengthFieldPrepender(2))
                    .addLast(new LengthFieldBasedFrameDecoder(65535, 0, 2, 0, 2))
                    .addLast(new LengthFieldBasedClientHandler());
        }
    }
}
