package com.my.bio;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * 类说明：Bio通信的客户端
 */
public class Client {

    public static void main(String[] args) throws IOException {
        //客户端启动必备
        Socket socket = null;
        ObjectInputStream inputStream = null;
        ObjectOutputStream outputStream = null;
        //实例化与服务端通信的输入输出流
        try {
            socket = new Socket();
            //连接服务器
            socket.connect(new InetSocketAddress("127.0.0.1", 10000));

            //TODO 注意：输出流要比输入流，更先定义
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
            /*向服务器输出请求*/
            outputStream.writeUTF("虚竹");
            outputStream.flush();

            //接收服务器的输出
            System.out.println(inputStream.readUTF());
        } finally {
            if (socket != null) {
                socket.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }
}
