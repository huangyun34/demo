package com.my.nio.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 类说明：nio通信客户端处理器
 */
public class NioClientHandle implements Runnable {

    private final String host;

    private final int port;

    private volatile boolean started;

    private Selector selector;

    private SocketChannel socketChannel;

    public NioClientHandle(String host, int port) {
        this.host = host;
        this.port = port;

        try {
            /*创建选择器的实例*/
            selector = Selector.open();
            /*创建SocketChannel的实例*/
            socketChannel = SocketChannel.open();
            //链接
            socketChannel.connect(new InetSocketAddress(host, port));
            //TODO 要链接成功后，才把通道设置问非阻塞，不然回连接不上
            /*设置通道为非阻塞模式*/
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
            /*设置好后，状态置为启动状态*/
            started = true;
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

    }

    public void stop(){
        started = false;
    }

    @Override
    public void run() {
//        try {
//            doConnect();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        //循环遍历selector
        while (started) {
            try {
                //无论是否有读写事件发生，selector每隔1s被唤醒一次
                selector.select(1000);
                //获取当前有哪些事件可以使用
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                //转换为迭代器
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    /*我们必须首先将处理过的 SelectionKey 从选定的键集合中删除。
                    如果我们没有删除处理过的键，那么它仍然会在主集合中以一个激活
                    的键出现，这会导致我们尝试再次处理它。*/
                    iterator.remove();
                    try {
                        handleInput(selectionKey);
                    } catch (Exception e) {
                        e.printStackTrace();
                        if (selectionKey != null) {
                            selectionKey.cancel();
                            if (selectionKey.channel() != null) {
                                selectionKey.channel().close();
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
        //selector关闭后会自动释放里面管理的资源
        if(selector != null) {
            try {
                selector.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void doConnect() throws IOException {
        /*非阻塞的连接*/
        if (socketChannel.connect(new InetSocketAddress(host, port))) {
            socketChannel.register(selector, SelectionKey.OP_READ);
        } else {
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
        }
    }

    //具体的事件处理方法
    private void handleInput(SelectionKey key) throws IOException {
        if (key.isValid()) {
            //获得关心当前事件的channel
            SocketChannel socketChannel = (SocketChannel) key.channel();
            //连接事件
            if (key.isAcceptable()) {
                if (socketChannel.finishConnect()) {
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else {
                    System.exit(1);
                }
            } else if (key.isReadable()) {
                //有数据可读事件
                //创建ByteBuffer，并开辟一个1M的缓冲区
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                //读取请求码流，返回读取到的字节数
                int readBytes = socketChannel.read(byteBuffer);
                //读取到字节，对字节进行编解码
                if (readBytes > 0) {
                    //将缓冲区当前的limit设置为position,position=0，用于后续对缓冲区的读取操作
                    byteBuffer.flip();
                    //根据缓冲区可读字节数创建字节数组
                    byte[] bytes = new byte[byteBuffer.remaining()];
                    //将缓冲区可读字节数组复制到新建的数组中
                    byteBuffer.get(bytes);

                    String result = new String(bytes,"UTF-8");
                    System.out.println("客户端收到消息：" + result);
                } else {
                    key.cancel();
                    socketChannel.close();
                }
            }
        }
    }

    private void doWrite(SocketChannel channel, String response) throws IOException {
        //将消息编码为字节数组
        byte[] bytes = response.getBytes();
        //根据数组容量创建ByteBuffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);
        //将字节数组复制到缓冲区
        byteBuffer.put(bytes);
        //flip操作
        byteBuffer.flip();
        //发送缓冲区的字节数组
        /*关心事件和读写网络并不冲突*/
        channel.write(byteBuffer);
    }

    //写数据对外暴露的API
    public void sendMsg(String msg) throws Exception{
        doWrite(socketChannel, msg);
    }

    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        System.out.println(socketChannel.connect(new InetSocketAddress("127.0.0.1", 9999)));
    }
}
