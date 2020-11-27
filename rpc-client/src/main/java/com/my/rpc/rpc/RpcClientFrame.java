package com.my.rpc.rpc;

import com.my.rpc.remote.vo.RegisterServiceVO;
import org.springframework.stereotype.Service;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * rpc框架客户端的代理部分
 * 总结：做两件事
 * 1。实现对远程服务器的访问
 * 2。从注册中心拿到服务提供者的服务地址
 */
@Service
public class RpcClientFrame {

    /*远程服务的代理对象，参数为客户端要调用的的服务*/
    public static<T> T getRemoteProxyObject(final Class<?> serviceInterface) throws Exception {
        /*获得远程服务的一个网络地址*/
        InetSocketAddress inetSocketAddress = getService(serviceInterface.getName());

        /*拿到一个代理对象，由这个代理对象通过网络进行实际的服务调用*/
        return (T)Proxy.newProxyInstance(serviceInterface.getClassLoader(),
                new Class<?>[]{serviceInterface},
                new DynProxy(serviceInterface, inetSocketAddress));
    }

    /*动态代理，实现对远程服务的访问*/
    private static class DynProxy implements InvocationHandler {
        //注册服务端接口
        private Class<?> serviceInterface;
        //注册服务端地址
        private InetSocketAddress inetSocketAddress;

        public DynProxy(Class<?> serviceInterface, InetSocketAddress inetSocketAddress) {
            this.serviceInterface = serviceInterface;
            this.inetSocketAddress = inetSocketAddress;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Socket socket = null;
            ObjectInputStream objectInputStream = null;
            ObjectOutputStream objectOutputStream = null;
            try {
                socket = new Socket();
                socket.connect(inetSocketAddress);
                objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

                //方法所在类名接口名称
                objectOutputStream.writeUTF(serviceInterface.getName());

                //方法名
                objectOutputStream.writeUTF(method.getName());

                //方法的入参类型
                objectOutputStream.writeObject(method.getTypeParameters());

                //方法入参的值
                objectOutputStream.writeObject(args);

                objectOutputStream.flush();

                objectInputStream = new ObjectInputStream(socket.getInputStream());
                System.out.println(serviceInterface + " remote exec success!");
                return objectInputStream.readObject();
            } finally {
                if (socket != null) {
                    socket.close();
                }
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
            }
        }
    }


    /*----------------以下和动态获得服务提供者有关------------------------------*/

    //随机值，随机获得服务器地址
    private static Random r = new Random();
    /*获得远程服务的地址*/
    private static InetSocketAddress getService(String serviceName) throws Exception {
        List<InetSocketAddress> serviceList = getServiceList(serviceName);
        InetSocketAddress inetSocketAddress = serviceList.get(r.nextInt(serviceList.size()));
        System.out.println("本次选择了服务器：" + inetSocketAddress);
        return inetSocketAddress;
    }

    /*获得服务提供者的地址*/
    private static List<InetSocketAddress> getServiceList(String serviceName) throws Exception {
        Socket socket = null;
        ObjectInputStream objectInputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            socket = new Socket();

            //注册中心地址
            socket.connect(new InetSocketAddress("127.0.0.1", 9998));

            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

            //需要获得服务提供者
            objectOutputStream.writeBoolean(true);

            //告诉注册中心服务名
            objectOutputStream.writeUTF(serviceName);
            objectOutputStream.flush();

            objectInputStream = new ObjectInputStream(socket.getInputStream());
            Set<RegisterServiceVO> registerServiceVOSet = (Set<RegisterServiceVO>) objectInputStream.readObject();
            //获取所以提供相关服务的服务地址
            List<InetSocketAddress> services = new ArrayList<>();
            for (RegisterServiceVO registerServiceVO : registerServiceVOSet) {
                services.add(new InetSocketAddress(registerServiceVO.getHost(), registerServiceVO.getPort()));
            }
            System.out.println("获得服务["+serviceName
                    +"]提供者的地址列表["+services+"]，准备调用.");
            return services;
        } finally {
            if (socket != null) {
                socket.close();
            }
            if (objectInputStream != null) {
                objectInputStream.close();
            }
            if (objectOutputStream != null) {
                objectOutputStream.close();
            }
        }
    }
}
