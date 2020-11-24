package com.my.rpc.config;

import com.my.rpc.remote.SendSms;
import com.my.rpc.rpc.RpcClientFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 启动时，开启RPC
 */
@Configuration
public class BeanConfig {

    @Autowired
    private RpcClientFrame rpcClientFrame;

    @Bean
    public SendSms getSendSmsService() throws Exception {
        return rpcClientFrame.getRemoteProxyObject(SendSms.class);
    }
}
