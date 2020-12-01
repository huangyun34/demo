package com.my.nio.nio;

import com.my.nio.Const;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * 类说明：nio通信服务端处理器
 */
public class NioServerHandle implements Runnable {

    private volatile boolean started;

    private ServerSocketChannel serverSocketChannel;

    private Selector selector;

    /**
     * 构造方法
     * @param port 指定要监听的端口号
     */
    public NioServerHandle(int port) {
        try {
            /*创建选择器的实例*/
            selector = Selector.open();
            /*创建ServerSocketChannel的实例*/
            serverSocketChannel = ServerSocketChannel.open();
            /*设置通道为非阻塞模式*/
            serverSocketChannel.configureBlocking(false);
            /*绑定端口*/
            serverSocketChannel.socket().bind(new InetSocketAddress(port));
            /*注册事件，表示关心客户端连接*/
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            started = true;
            System.out.println("服务器已启动，端口号："+port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (started) {
            try {
                /*获取当前有哪些事件*/
                if (selector.select(1000) < 1) {
                    continue;
                }
                /*获取事件的集合*/
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> selectionKeyIterator = selectionKeys.iterator();
                while (selectionKeyIterator.hasNext()) {
                    SelectionKey selectionKey = selectionKeyIterator.next();
                    /*我们必须首先将处理过的 SelectionKey 从选定的键集合中删除。
                    如果我们没有删除处理过的键，那么它仍然会在主集合中以一个激活
                    的键出现，这会导致我们尝试再次处理它。*/
                    selectionKeyIterator.remove();
                    //处理逻辑
                    handleInput(selectionKey);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /*处理事件的发生*/
    private void handleInput(SelectionKey key) throws IOException {
        if (key.isValid()) {
            /*处理新接入的客户端的请求*/
            if (key.isAcceptable()) {
                /*获取关心当前事件的Channel*/
                ServerSocketChannel serverSocketChannel = (ServerSocketChannel)key.channel();
                /*接受连接*/
                SocketChannel socketChannel = serverSocketChannel.accept();
                System.out.println("==========建立连接=========");
                /*设置为非阻塞模式*/
                socketChannel.configureBlocking(false);
                /*关注读事件*/
                socketChannel.register(selector, SelectionKey.OP_READ);
            }
            /*处理对端的发送的数据*/
            if (key.isReadable()) {
                SocketChannel socketChannel = (SocketChannel)key.channel();
                /*创建ByteBuffer，开辟一个缓冲区*/
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                /*从通道里读取数据，然后写入buffer*/
                int readBytes = socketChannel.read(byteBuffer);
                if (readBytes > 0) {
                    /*将缓冲区当前的limit设置为position,position=0，
                    用于后续对缓冲区的读取操作*/
                    byteBuffer.flip();
                    /*根据缓冲区可读字节数创建字节数组*/
                    byte[] bytes = new byte[byteBuffer.remaining()];
                    /*将缓冲区可读字节数组复制到新建的数组中*/
                    byteBuffer.get(bytes);
                    String message = new String(bytes, "utf-8");
                    System.out.println("服务器收到消息："+message);
                    /*处理数据*/
                    String response = Const.response(message);
                    /*发送应答消息*/
                    doWrite(socketChannel, response);
                }
            }
        }
    }

    /*发送应答消息*/
    private void doWrite(SocketChannel sc,String response) throws IOException {
        byte[] bytes = response.getBytes();
        ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);
        byteBuffer.put(bytes);
        byteBuffer.flip();
        sc.write(byteBuffer);
    }

    public void stop(){
        started = false;
    }
}
