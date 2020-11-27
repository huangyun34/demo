package com.my.rpc.remote.vo;

import java.io.Serializable;

/**
 * 注册中心注册服务的实体类
 */
public class RegisterServiceVO implements Serializable {

    private final String host;

    private final int port;

    public RegisterServiceVO(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }
}
