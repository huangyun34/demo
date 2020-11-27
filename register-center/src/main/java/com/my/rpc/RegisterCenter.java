package com.my.rpc;

import com.my.rpc.remote.vo.RegisterServiceVO;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

/**
 * 类说明：服务注册中心，服务提供者在启动时需要在注册中心登记自己的信息
 */
@Service
public class RegisterCenter {
    /*key表示服务名，value代表服务提供者地址的集合*/
    private static final Map<String, Set<RegisterServiceVO>> SERVICE_HOLDER = new HashMap<>();

    //注册中心端口号port
    private static final int PORT = 9998;

    /*服务注册，考虑到可能有多个提供者同时注册,使用锁*/
    private static synchronized void registerService(String serviceName,
                                                     String host,int port) {
        //获得当前服务的已有地址集合
        Set<RegisterServiceVO> registerServiceVOS = SERVICE_HOLDER.get(serviceName);
        if (null == registerServiceVOS) {
            //已有地址集合为空，新增集合
            Set<RegisterServiceVO> serviceVOSet = new HashSet<>();
            serviceVOSet.add(new RegisterServiceVO(host, port));
            SERVICE_HOLDER.put(serviceName, serviceVOSet);
        } else {
            registerServiceVOS.add(new RegisterServiceVO(host, port));
        }
        System.out.println("服务已注册["+serviceName+"]，" +
                "地址["+host+"]，端口["+port+"]");
    }

    /*取出服务提供者*/
    private static Set<RegisterServiceVO> getService(String serviceName) {
        return SERVICE_HOLDER.get(serviceName);
    }

    /*处理服务请求的任务，其实无非就是两种服务：
    1、服务注册服务
    2、服务查询服务
    */
    private static class ServerTask implements Runnable{
        private Socket client;

        public ServerTask(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {

            try (ObjectInputStream inputStream = new ObjectInputStream(client.getInputStream());
                 ObjectOutputStream outputStream = new ObjectOutputStream(client.getOutputStream());) {
                /*检查当前请求是注册服务还是获得服务*/
                boolean isGetService = inputStream.readBoolean();
                /*服务查询服务，获得服务提供者*/
                if (isGetService) {
                    String serviceName = inputStream.readUTF();
                    /*取出服务提供者集合*/
                    Set<RegisterServiceVO> registerServiceVOSet = getService(serviceName);
                    /*返回给客户端*/
                    outputStream.writeObject(registerServiceVOSet);
                    outputStream.flush();
                    System.out.println("将已注册的服务[" + serviceName + "]提供给客户端");
                } else {
                    /*服务注册服务*/
                    /*取得新服务提供方的服务名、ip和端口*/
                    String serviceName = inputStream.readUTF();
                    String host = inputStream.readUTF();
                    int port = inputStream.readInt();
                    /*在注册中心保存*/
                    registerService(serviceName, host, port);
                    outputStream.writeBoolean(true);
                    outputStream.flush();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (null != client) {
                    try {
                        client.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /*启动注册服务*/
    public void startService() throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(PORT));
        System.out.println("服务注册中心 on:"+PORT+":运行");
        try {
            while (true) {
//                new Thread(new ServerTask(serverSocket.accept())).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            serverSocket.close();
        }
    }

    @PostConstruct
    public void init() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    startService();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
