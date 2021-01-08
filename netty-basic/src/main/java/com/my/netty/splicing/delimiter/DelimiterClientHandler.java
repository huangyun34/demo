package com.my.netty.splicing.delimiter;

import com.my.netty.splicing.CommonConstants;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 类说明：自己的业务处理
 * @author huangyun
 */
public class DelimiterClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private AtomicInteger counter = new AtomicInteger(0);
    /*** 客户端读取到网络数据后的处理*/
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        System.out.println("client Accept["+byteBuf.toString(CharsetUtil.UTF_8)
                +"] and the counter is:"+counter.incrementAndGet());
    }

    /*** 客户端被通知channel活跃后，做事*/
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf byteBuf = null;
        String msg = "吃火锅，红烧活兔，火爆黄鳝，青蛙，羊肉汤，凉虾，豆腐脑，烧烤，锅盔，猪儿粑等等，哇啊啊啊啊啊啊，嗝饿了。。。" + CommonConstants.DELIMITER_SYMBOL;
        for (int i = 0; i < 10; i++) {
            byteBuf = Unpooled.buffer();
            byteBuf.writeBytes(msg.getBytes());
            ctx.writeAndFlush(byteBuf);
        }
    }

    /*** 发生异常后的处理*/
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
