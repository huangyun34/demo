package com.my.netty.splicing.fixed;

import com.my.netty.splicing.CommonConstants;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;

/**
 * 注意中文乱码问题，定长设置的长度是字符串转换为字节数字的长度
 * @author huangyun
 */
public class FixedLengthServer {

    public static void main(String[] args) throws InterruptedException {
        new FixedLengthServer().start();
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
            System.out.println("服务器已启动，等待接收");
            channelFuture.channel().closeFuture().sync();
        } finally {
            eventLoopGroup.shutdownGracefully().sync();
        }
    }

    private class ChannelInitializerImp extends ChannelInitializer<Channel> {

        @Override
        protected void initChannel(Channel channel) throws Exception {
            channel.pipeline()
                    .addLast(new FixedLengthFrameDecoder(CommonConstants.FIXED_CLIENT_MSG.getBytes().length))
                    .addLast(new FixedLengthServerHannler());
        }
    }
}
