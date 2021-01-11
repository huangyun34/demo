package com.my.netty.splicing.delimiter;

import com.my.netty.splicing.CommonConstants;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;

import java.net.InetSocketAddress;

/**
 * 半包粘包问题解决：包尾部增加分隔符
 * @author huangyun
 */
public class DelimiterEchoClient {

    private final String host;

    private DelimiterEchoClient(String host) {
        this.host = host;
    }

    private void start() throws InterruptedException {
        /*线程组*/
        final EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            /*客户端启动必须*/
            Bootstrap bootstrap = new Bootstrap()
                    /*将线程组传入*/
                    .group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    /*配置要连接服务器的ip地址和端口*/
                    .remoteAddress(new InetSocketAddress(host, CommonConstants.SERVER_PORT))
                    /*客户端发送一个连接请求，就会新启一个socket通信，也就是channel，
                    所以下面这段代码的作用就是为这个子channel增加handle*/
                    .handler(new ChannelInitializerImp());
            /*异步绑定到服务器，sync()会阻塞直到完成*/
            ChannelFuture channelFuture = bootstrap.connect().sync();
            /*阻塞直到服务器的channel关闭*/
            channelFuture.channel().closeFuture().sync();
        } finally {
            eventLoopGroup.shutdownGracefully().sync();/*优雅关闭线程组*/
        }

    }

    private class ChannelInitializerImp extends ChannelInitializer<Channel> {

        @Override
        protected void initChannel(Channel channel) throws Exception {
            ByteBuf byteBuf = Unpooled.copiedBuffer(CommonConstants.DELIMITER_SYMBOL.getBytes());
            channel.pipeline()
                    .addLast(new DelimiterBasedFrameDecoder(1024, byteBuf))
                    .addLast(new DelimiterClientHandler());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new DelimiterEchoClient("127.0.0.1").start();
    }
}
