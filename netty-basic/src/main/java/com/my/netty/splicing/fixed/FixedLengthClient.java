package com.my.netty.splicing.fixed;

import com.my.netty.splicing.CommonConstants;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;

import java.net.InetSocketAddress;

/**
 * 注意中文乱码问题，定长设置的长度是字符串转换为字节数字的长度
 * @author huangyun
 */
public class FixedLengthClient {

    public static void main(String[] args) throws InterruptedException {
        new FixedLengthClient().start();
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
                    .addLast(new FixedLengthFrameDecoder(CommonConstants.FIXED_SERVER_MSG.getBytes().length))
                    .addLast(new FixedLengthClientHandler());
        }
    }
}
