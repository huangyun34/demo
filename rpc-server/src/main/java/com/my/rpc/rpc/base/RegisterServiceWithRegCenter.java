package com.my.rpc.rpc.base;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 类说明：注册服务,引入了服务的注册和发现机制
 */
@Service
public class RegisterServiceWithRegCenter {

    /*本地可提供服务的一个名单，用缓存实现*/
    private static final Map<String, Class> SERVER_CACHE = new ConcurrentHashMap<>();

    /*本地注册中心地址*/
    private static final int REGISTER_CENTER_PORT = 9998;

    /*往远程注册服务器注册本服务,同时在本地注册本服务*/
    public void regRemote(String serviceName, String host, int port, Class impl)
            throws Throwable{
        //登记到注册中心

        Socket socket = null;
        ObjectInputStream inputStream = null;
        ObjectOutputStream outputStream = null;

        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(REGISTER_CENTER_PORT));

            outputStream = new ObjectOutputStream(socket.getOutputStream());

            /*告诉注册中心是注册服务事件*/
            outputStream.writeBoolean(false);

            /*服务提供方的接口名称*/
            outputStream.writeUTF(serviceName);

            /*服务提供方的IP*/
            outputStream.writeUTF(host);

            /*服务提供方的端口*/
            outputStream.writeInt(port);
            outputStream.flush();

            inputStream = new ObjectInputStream(socket.getInputStream());
            if (inputStream.readBoolean()) {
                System.out.println("服务["+serviceName+"]注册成功!");
            }

            /*可提供服务放入本地缓存*/
            SERVER_CACHE.put(serviceName, impl);
        } catch (IOException e) {
            e.printStackTrace();
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

    /*获取服务*/
    public Class getLocalService(String serviceName) {
        return SERVER_CACHE.get(serviceName);
    }
}
