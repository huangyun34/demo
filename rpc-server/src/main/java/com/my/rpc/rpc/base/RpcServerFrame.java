package com.my.rpc.rpc.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 类说明：rpc框架的服务端部分
 */
@Service
public class RpcServerFrame {

    @Autowired
    private RegisterServiceWithRegCenter registerServiceWithRegCenter;

    /*处理服务请求任务*/
    private static class ServerTask implements Runnable {

        private Socket socket;

        private RegisterServiceWithRegCenter registerServiceWithRegCenter;

        public ServerTask(Socket socket, RegisterServiceWithRegCenter registerServiceWithRegCenter) {
            this.socket = socket;
            this.registerServiceWithRegCenter = registerServiceWithRegCenter;
        }

        @Override
        public void run() {
            try (ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                 ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream())) {

                /*方法所在类名接口名*/
                String serviceName = inputStream.readUTF();
                /*方法的名字*/
                String methodName = inputStream.readUTF();
                /*方法的参数类型*/
                Class<?>[] paramTypes = (Class<?>[])inputStream.readObject();
                /*方法的入参的值*/
                Object[] args = (Object[])inputStream.readObject();

                /*从容器中拿到服务的Class对象*/
                Class impl = registerServiceWithRegCenter.getLocalService(serviceName);
                if (null == impl) {
                    throw new ClassNotFoundException(serviceName + "not found");
                }

                /*通过反射，执行实际的服务*/
                Method method = impl.getMethod(methodName, paramTypes);

                //注意newInstance初始化个实例
                Object result = method.invoke(impl.newInstance(), args);

                /*将服务的执行结果通知调用者*/
                outputStream.writeObject(result);
                outputStream.flush();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 提供服务
     * @param serviceName
     * @param host
     * @param port
     * @param impl
     * @throws Throwable
     */
    public void startService(String serviceName, String host, int port, Class impl) throws Throwable{
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(port));
        System.out.println("RPC server on:"+port+":运行");
        registerServiceWithRegCenter.regRemote(serviceName, host, port, impl);
        try {
            while (true) {
                //线程不要忘了启动
                new Thread(new ServerTask(serverSocket.accept(), registerServiceWithRegCenter)).start();
            }
        } finally {
            serverSocket.close();
        }
    }
}
