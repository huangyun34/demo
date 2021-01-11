package com.my.netty.splicing.lengthfieldbased;

import com.my.netty.splicing.CommonConstants;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

public class LengthFieldBasedServer {

    public static void main(String[] args) throws InterruptedException {
        new LengthFieldBasedServer().start();
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
                    .addLast(new LengthFieldBasedFrameDecoder(65535, 0, 2, 0, 2))
                    .addLast(new LengthFieldPrepender(2))
                    .addLast(new LengthFieldBasedServerHandler());
        }
    }
}
