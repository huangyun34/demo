package com.my.admin.javabasic.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Test {

    public static void main(String[] args) throws UnknownHostException {
        String host = "www.google.com.hk";
        InetAddress ip = InetAddress.getByName(host);
        System.out.println(ip.getHostName());
        System.out.println(ip.getHostAddress());
    }
}
